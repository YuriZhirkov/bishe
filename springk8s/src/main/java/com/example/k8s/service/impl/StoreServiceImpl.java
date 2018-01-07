package com.example.k8s.service.impl;

import com.example.k8s.mapper.StoreMapper;
import com.example.k8s.model.Store;
import com.example.k8s.service.StoreService;
import com.example.k8s.untils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zzg on 2018/1/2.
 */
@Service(value = "storeService")
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;//这里会报错，但是并不会影响
    @Override
    public int add(Store store) {
        Date date = new Date();
        store.setStoretime(date.getTime());
        storeMapper.insertSelective(store);
        return store.getId();
    }

    @Override
    public Store selectByStorename(String storename) {
        return storeMapper.selectByStorename(storename);
    }

    @Override
    public void delete(Integer[] ids) {
        if (ids != null && ids.length > 0){
            for (int i=0 ; i < ids.length ; i++) {
                if (storeMapper.selectByPrimaryKey(ids[i]) != null){
                    storeMapper.deleteByPrimaryKey(ids[i]);
                }
            }
        }
    }

    @Override
    public void update(Store store) {
        storeMapper.updateByPrimaryKeySelective(store);
    }

    @Override
    public Store selectByPrimaryKey(Integer id) {
        return storeMapper.selectByPrimaryKey(id);
    }


    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public PageResult<Store> selectAllStore(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageResult<Store> pageResult = new PageResult<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Store> recordList  =  storeMapper.selectAllStore();
        PageInfo<Store> pageInfo = new PageInfo<>(recordList);
        pageResult.setRecordsTotal(pageInfo.getTotal());
        pageResult.setItems(recordList);
        return pageResult;
    }
}
