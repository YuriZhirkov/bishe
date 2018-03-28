package com.example.k8s.service;

import com.example.k8s.dto.IOrderList;
import com.example.k8s.model.Orders;

import java.util.List;

/**
 * Created by zzg on 2018/3/28.
 */
public interface ShoppingCartService {
    //添加订单到购物车
    void add(Orders orders);
    //从购物中删除订单
    void delete(Integer userId,String orderId);
    //从购物车中查询所有订单
    List<Orders> list(IOrderList iOrderList);
    //从购物车中查询某个订单的详情
    Orders get(Integer userId,String orderId);
}
