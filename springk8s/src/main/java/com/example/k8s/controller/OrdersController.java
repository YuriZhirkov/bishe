package com.example.k8s.controller;

import com.example.k8s.dto.IOrderList;
import com.example.k8s.dto.IOrdersDelete;
import com.example.k8s.model.Orders;
import com.example.k8s.service.OrdersService;
import com.example.k8s.untils.ApiResponse;
import com.example.k8s.untils.SimpleApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zzg on 2018/1/1.
 */
@Controller
@RequestMapping(value = "/bonsai/orders")
public class OrdersController {
    private final static Logger logger =  LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private OrdersService ordersService;

    /**
     * 添加订单
     * @param orders
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse add(@RequestBody Orders orders){
        if (orders == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "添加订单成功", ordersService.add(orders));
        } catch (Exception e) {
            logger.error("/bonsai/orders/add error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，添加订单失败");
        }
    }


    /**
     * 删除订单
     * @param iOrdersDelete
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse delete(@RequestBody IOrdersDelete iOrdersDelete){
        if (iOrdersDelete == null || iOrdersDelete.getIds().length < 0) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        try {
            ordersService.delete(iOrdersDelete.getIds());
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error("/bonsai/Orders/delete error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，删除失败");
        }
    }


    /**
     * 更新订单
     * @param orders
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse update(@RequestBody Orders orders){
        if (orders == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "商品的参数不能为空");
        }
        Orders temp = ordersService.selectByPrimaryKey(orders.getId());
        if (temp == null){
            return new ApiResponse(ApiResponse.CODE_NOT_EXIST, "订单不存在");
        }
        try {
            ordersService.update(orders);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "更新成功");
        } catch (Exception e) {
            logger.error("/bonsai/orders/update error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，更新失败");
        }
    }

    /**得到某个订单的信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ApiResponse get(@PathVariable("id") int id){
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取订单信息成功",ordersService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("/bonsai/orders/get/{id} error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，获取订单信息失败");
        }
    }


    /**
     * 得到所有订单的信息
     * @param iOrderList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse list(@RequestBody IOrderList iOrderList){

        // https://www.cnblogs.com/xiaoxinwt/p/5329840.html Mybatis 的分页插件PageHelper-4.1.1的使用
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取所有订单信息成功", ordersService.selectByGoodsname(iOrderList));
        } catch (Exception e) {
            logger.error("/list"+" error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "获取所有订单信息失败");
        }
    }
}
