package com.example.k8s.service.impl;

import com.example.k8s.dto.IOrderList;
import com.example.k8s.mapper.OrdersMapper;
import com.example.k8s.model.Orders;
import com.example.k8s.service.OrdersService;
import com.example.k8s.untils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zzg on 2018/1/1.
 */
@Service(value = "ordersService")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;//这里会报错，但是并不会影响
    @Override
    public String add(Orders orders) {
        Date date = new Date();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        orders.setState(0);//未支付
        orders.setId(uuid);
        orders.setDate(date.getTime());
        ordersMapper.insertSelective(orders);
        return orders.getId();
    }


    @Override
    public void delete(String[] ids) {
        if (ids != null && ids.length > 0){
            for (int i=0 ; i < ids.length ; i++) {
                if (ordersMapper.selectByPrimaryKey(ids[i]) != null){
                    ordersMapper.deleteByPrimaryKey(ids[i]);
                }
            }
        }
    }

    @Override
    public void update(Orders orders) {
        ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public Orders selectByPrimaryKey(String id) {
        return ordersMapper.selectByPrimaryKey(id);
    }


    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public PageResult<Orders> selectByGoodsname(IOrderList iOrderList) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageResult<Orders> pageResult = new PageResult<>();
        PageHelper.startPage(iOrderList.getPageNum(), iOrderList.getPageSize());
        Map<String, String> map = new HashMap<>();
        map.put("goodsname",iOrderList.getName());
        map.put("username",iOrderList.getUsername());
        List<Orders> recordList  =  ordersMapper.selectByGoodsname(map);
        PageInfo<Orders> pageInfo = new PageInfo<>(recordList);
        pageResult.setRecordsTotal(pageInfo.getTotal());
        pageResult.setItems(recordList);
        return pageResult;
    }

    @Override
    public PageResult<Orders> selectBySellid(IOrderList iOrderList) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageResult<Orders> pageResult = new PageResult<>();
        PageHelper.startPage(iOrderList.getPageNum(), iOrderList.getPageSize());
        List<Orders> recordList  =  ordersMapper.selectBySellid(iOrderList.getSellid());
        PageInfo<Orders> pageInfo = new PageInfo<>(recordList);
        pageResult.setRecordsTotal(pageInfo.getTotal());
        pageResult.setItems(recordList);
        return pageResult;
    }

    @Override
    public PageResult<Orders> selectByBuyid(IOrderList iOrderList) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageResult<Orders> pageResult = new PageResult<>();
        PageHelper.startPage(iOrderList.getPageNum(), iOrderList.getPageSize());
        List<Orders> recordList  =  ordersMapper.selectByBuyid(iOrderList.getSellid());
        PageInfo<Orders> pageInfo = new PageInfo<>(recordList);
        pageResult.setRecordsTotal(pageInfo.getTotal());
        pageResult.setItems(recordList);
        return pageResult;
    }
}
