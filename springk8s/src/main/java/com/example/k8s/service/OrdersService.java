package com.example.k8s.service;

import com.example.k8s.dto.IOrderList;
import com.example.k8s.model.Orders;
import com.example.k8s.untils.PageResult;

/**
 * Created by zzg on 2018/1/1.
 */
public interface OrdersService {

    int add(Orders orders);

    PageResult<Orders> selectByGoodsname(IOrderList iOrderList);

    void delete(Integer[] ids);

    void update(Orders orders);

    Orders selectByPrimaryKey(Integer id);
}
