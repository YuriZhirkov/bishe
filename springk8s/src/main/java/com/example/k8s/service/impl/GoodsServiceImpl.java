package com.example.k8s.service.impl;

import com.example.k8s.dto.IGoodsList;
import com.example.k8s.mapper.GoodsMapper;
import com.example.k8s.model.Goods;
import com.example.k8s.service.GoodsService;
import com.example.k8s.service.MySearchService;
import com.example.k8s.untils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzg on 2018/1/1.
 */
@Service(value = "goodsService")
public class GoodsServiceImpl implements GoodsService {

    private String indexName = "zzg";
    private String typeName = "goods";

    @Autowired
    private GoodsMapper goodsMapper;//这里会报错，但是并不会影响

    @Autowired
    private MySearchService mySearchService;

    @Override
    public int add(Goods goods) {
        goodsMapper.insertSelective(goods);
        //添加到es中
        List<Object> list = new ArrayList<>();
        list.add(goods);
        try {
            mySearchService.index(list,indexName,typeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods.getId();
    }

    @Override
    public Goods selectByGoodsname(String goodsname) {
        return goodsMapper.selectByGoodsname(goodsname);
    }

    @Override
    public void delete(Integer[] ids) {
        if (ids != null && ids.length > 0){
            for (int i=0 ; i < ids.length ; i++) {
                if (goodsMapper.selectByPrimaryKey(ids[i]) != null){
                    goodsMapper.deleteByPrimaryKey(ids[i]);
                    try {
                        mySearchService.delete(indexName,typeName,ids[i].toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void update(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
        try {
            mySearchService.update(indexName,typeName,goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }


    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public PageResult<Goods> selectAllGoods(IGoodsList iGoodsList) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageResult<Goods> pageResult = new PageResult<>();
        PageHelper.startPage(iGoodsList.getPageNum(), iGoodsList.getPageSize());
        List<Goods> recordList = new ArrayList<Goods>();
        if (iGoodsList.getUserId() != null){
            PageHelper.startPage(iGoodsList.getPageNum(), iGoodsList.getPageSize());
            recordList  =  goodsMapper.selectAllGoodsByUserid(iGoodsList.getUserId());
        } else {
            if (StringUtils.isNotBlank(iGoodsList.getCondition())){
                PageHelper.startPage(iGoodsList.getPageNum(), iGoodsList.getPageSize());
                recordList  =  goodsMapper.search(iGoodsList.getCondition());
            }else {
                PageHelper.startPage(iGoodsList.getPageNum(), iGoodsList.getPageSize());
                recordList  =  goodsMapper.selectAllGoods();
            }
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(recordList);
        pageResult.setRecordsTotal(pageInfo.getTotal());
        pageResult.setItems(recordList);
        return pageResult;
    }

    //搜索
    @Override
    public PageResult<Goods> search(IGoodsList iGoodsList) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageResult<Goods> pageResult = null;

        try {
            pageResult = mySearchService.qurey(indexName,typeName,iGoodsList.getCondition());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageResult;
    }



}
