package com.example.k8s.service.impl;

import com.example.k8s.dto.IOrderList;
import com.example.k8s.model.Orders;
import com.example.k8s.service.ShoppingCartService;
import com.example.k8s.untils.JsonUtils;
import com.example.k8s.untils.ListPageUtil;
import com.example.k8s.untils.RedisUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzg on 2018/3/28.
 */
@Service(value = "shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    private RedisUnits redisUnits;

    @Override
    public void add(Orders orders) {
        String userid = String.valueOf(orders.getBuyid());
        String json = (String) redisUnits.getValue(userid);
        //解析json字符串
        List<Orders> ordersList = JsonUtils.jsonToList(json,Orders.class);
        if (ordersList == null || ordersList.size()<=0){
            ordersList = new ArrayList<>();
            ordersList.add(orders);
            String ret = JsonUtils.objectToJson(ordersList);
            redisUnits.setValue(userid,ret);
        } else {
            for (Orders orders1:ordersList){
                if (orders1.getBuyid().equals(orders.getBuyid()) && orders1.getId().equals(orders.getId())){
                    ordersList.remove(orders1);
                    break;
                }
            }
            ordersList.add(orders);
            String ret = JsonUtils.objectToJson(ordersList);
            redisUnits.setValue(userid,ret);
        }

    }

    @Override
    public void delete(Integer userId, String orderId) {
        String userid = String.valueOf(userId);
        String json = (String) redisUnits.getValue(userid);
        //解析json字符串
        List<Orders> ordersList = JsonUtils.jsonToList(json,Orders.class);
        if (ordersList != null && ordersList.size()>0){
            for (Orders orders:ordersList){
                if (userId.equals(orders.getBuyid()) && orderId.equals(orders.getId())){
                    ordersList.remove(orders);
                    break;
                }
            }
            String ret = JsonUtils.objectToJson(ordersList);
            redisUnits.setValue(userid,ret);

        }

    }

    @Override
    public List<Orders> list(IOrderList iOrderList) {
        String userid = String.valueOf(iOrderList.getBuyid());
        String json = (String) redisUnits.getValue(userid);
        //解析json字符串
        List<Orders> ordersList = JsonUtils.jsonToList(json,Orders.class);
        ListPageUtil<Orders> listPageUtil = new ListPageUtil<Orders>(ordersList,iOrderList.getPageNum(), iOrderList.getPageSize());
        List<Orders> pagedList = listPageUtil.getPagedList();
        if (ordersList==null){
            return new ArrayList<>();
        } else {
            return pagedList;
        }
    }

    @Override
    public Orders get(Integer userId, String orderId) {
        String userid = String.valueOf(userId);
        String json = (String) redisUnits.getValue(userid);
        //解析json字符串
        List<Orders> ordersList = JsonUtils.jsonToList(json,Orders.class);
        if (ordersList != null && ordersList.size()>0) {
            for (Orders orders : ordersList) {
                if (userId.equals(orders.getBuyid()) && orderId.equals(orders.getId())) {
                    return orders;
                }
            }
        }
        return new Orders();
    }
}
