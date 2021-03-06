package com.example.k8s.service.impl;

import com.example.k8s.dto.IOrderList;
import com.example.k8s.mapper.OrdersMapper;
import com.example.k8s.mapper.UserMapper;
import com.example.k8s.model.Orders;
import com.example.k8s.model.User;
import com.example.k8s.service.ShoppingCartService;
import com.example.k8s.untils.JsonUtils;
import com.example.k8s.untils.ListPageUtil;
import com.example.k8s.untils.RedisUnits;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzg on 2018/3/28.
 */
@Service(value = "shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private RedisUnits redisUnits;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public void add(Orders orders) {
        String userid = String.valueOf(orders.getBuyid());
        String json = (String) redisUnits.getValue(userid);
        if (StringUtils.isBlank(json)) {
            List<Orders> ordersList = new ArrayList<>();
            ordersList.add(orders);
            String ret = JsonUtils.objectToJson(ordersList);
            redisUnits.setValue(userid, ret);

        } else {
            List<Orders> ordersList = JsonUtils.jsonToList(json, Orders.class);
            for (Orders od : ordersList) {
                if (od.getBuyid().equals(orders.getBuyid())  && od.getGoodsid().equals(orders.getGoodsid())){
                    orders.setGoodsnumber(orders.getGoodsnumber()+od.getGoodsnumber());
                    ordersList.remove(od);
                    break;
                }
            }
            ordersList.add(orders);
            String ret = JsonUtils.objectToJson(ordersList);
            redisUnits.setValue(userid, ret);
        }

    }

    @Override
    public void delete(Integer userId, String goodsid) {
        String userid = String.valueOf(userId);
        String json = (String) redisUnits.getValue(userid);
        //解析json字符串
        if (StringUtils.isNotBlank(json)){
            List<Orders> ordersList = JsonUtils.jsonToList(json, Orders.class);
            if (ordersList != null && ordersList.size() > 0) {
                for (Orders orders : ordersList) {
                    if (userId.equals(orders.getBuyid()) && goodsid.equals(orders.getGoodsid())) {
                        ordersList.remove(orders);
                        break;
                    }
                }
                if (ordersList == null || ordersList.size()<=0){
                    redisUnits.setValue(userid, null);
                }
                String ret = JsonUtils.objectToJson(ordersList);
                redisUnits.setValue(userid, ret);

            }
        }


    }

    @Override
    public List<Orders> list(IOrderList iOrderList) {
        String userid = String.valueOf(iOrderList.getBuyid());
        String json = (String) redisUnits.getValue(userid);
        //解析json字符串
        if (StringUtils.isBlank(json)){
            return new ArrayList<>();
        }
        List<Orders> ordersList = JsonUtils.jsonToList(json, Orders.class);
        ListPageUtil<Orders> listPageUtil = new ListPageUtil<Orders>(ordersList, iOrderList.getPageNum(), iOrderList.getPageSize());
        List<Orders> pagedList = listPageUtil.getPagedList();
        if (ordersList == null) {
            return new ArrayList<>();
        } else {
            return pagedList;
        }
    }

    @Override
    public Orders get(Integer userId, String goodsid) {
        String userid = String.valueOf(userId);
        String json = (String) redisUnits.getValue(userid);
        if (StringUtils.isBlank(json)){
            return null;
        }
        //解析json字符串
        List<Orders> ordersList = JsonUtils.jsonToList(json, Orders.class);
        if (ordersList != null && ordersList.size() > 0) {
            for (Orders orders : ordersList) {
                if (userId.equals(orders.getBuyid()) && goodsid.equals(orders.getGoodsid())) {
                    return orders;
                }
            }
        }
        return new Orders();
    }

    @Override
    public void settlement(List<Orders> orders) {
        //获取用户id;
        int userId = orders.get(0).getBuyid();
        User user = userMapper.selectByPrimaryKey(userId);
        double sum = 0.0;
        for (Orders os : orders) {
            ordersMapper.insertSelective(os);
            sum = sum + os.getGoodsnumber() * Integer.parseInt(os.getPrice());
        }
        user.setSurplusmoney(user.getSurplusmoney() - (int) sum);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteAll(Integer userId) {
        redisUnits.setValue(String.valueOf(userId), null);
    }


}
