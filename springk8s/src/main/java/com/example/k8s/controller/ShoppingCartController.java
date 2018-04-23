package com.example.k8s.controller;

import com.example.k8s.dto.IOrderList;
import com.example.k8s.dto.IOrderRedis;
import com.example.k8s.dto.IOrdersDelete;
import com.example.k8s.model.Orders;
import com.example.k8s.service.ShoppingCartService;
import com.example.k8s.untils.ApiResponse;
import com.example.k8s.untils.SimpleApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by zzg on 2018/3/28.
 */
@Controller
@RequestMapping(value = "/bonsai/ShoppingCart")
@CrossOrigin
public class ShoppingCartController {
    private final static Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加订单到购物车
     *
     * @param orders
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ApiResponse add(@RequestBody Orders orders) {
        if (orders == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        try {
            shoppingCartService.add(orders);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "添加订单到购物车");
        } catch (Exception e) {
            logger.error("/bonsai/orders/add error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，添加订单到购物车失败");
        }
    }


    /**
     * 删除购物车中的订单
     *
     * @param iOrderRedis
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ApiResponse delete(@RequestBody IOrderRedis iOrderRedis) {
        if (iOrderRedis == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        try {
            shoppingCartService.delete(iOrderRedis.getUserId(), iOrderRedis.getGoodsid());
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error("/bonsai/Orders/delete error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，删除失败");
        }
    }


    /**
     * 得到购物车中的某个订单的信息
     *
     * @param iOrderRedis
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ApiResponse get(@RequestBody IOrderRedis iOrderRedis) {
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取订单信息成功", shoppingCartService.get(iOrderRedis.getUserId(), iOrderRedis.getGoodsid()));
        } catch (Exception e) {
            logger.error("/bonsai/orders/get/{id} error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，获取订单信息失败");
        }
    }


    /**
     * 得到所有订单的信息
     *
     * @param iOrderList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ApiResponse list(@RequestBody IOrderList iOrderList) {

        // https://www.cnblogs.com/xiaoxinwt/p/5329840.html Mybatis 的分页插件PageHelper-4.1.1的使用
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取所有订单信息成功", shoppingCartService.list(iOrderList));
        } catch (Exception e) {
            logger.error("/list" + " error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "获取所有订单信息失败");
        }
    }

    /**
     * 得到所有订单的信息
     *
     * @param orders
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/settlement", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ApiResponse settlement(@RequestBody List<Orders> orders) {

        // https://www.cnblogs.com/xiaoxinwt/p/5329840.html Mybatis 的分页插件PageHelper-4.1.1的使用
        try {
            if (orders==null || orders.isEmpty()){
                return new SimpleApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数为空");
            }
            shoppingCartService.settlement(orders);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "结算成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("/settlement" + " error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "结算失败");
        }
    }

    /**
     * 删除购物车中的所有订单
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ApiResponse delete( @RequestParam("userId")  Integer userId ) {
        try {
            shoppingCartService.deleteAll(userId);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error("/bonsai/Orders/deleteAll error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，删除失败");
        }
    }


}
