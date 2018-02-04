package com.example.k8s.controller;

import com.example.k8s.dto.IGoodsDelete;
import com.example.k8s.dto.IGoodsList;
import com.example.k8s.model.Goods;
import com.example.k8s.service.GoodsService;
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
@RequestMapping(value = "/bonsai/goods")
@CrossOrigin
public class GoodsController {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private GoodsService goodsService;

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse add(@RequestBody Goods goods){
        if (goods == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        Goods temp = goodsService.selectByGoodsname(goods.getGoodsname());
        if (temp != null){
            return new ApiResponse(ApiResponse.CODE_EXIST, "商品已经存在");
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "添加商品成功", goodsService.add(goods));
        } catch (Exception e) {
            logger.error("/bonsai/goods/add error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，添加商品失败");
        }
    }


    /**
     * 删除商品
     * @param iGoodsDelete
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse delete(@RequestBody IGoodsDelete iGoodsDelete){
        if (iGoodsDelete == null || iGoodsDelete.getIds().length < 0) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        try {
            goodsService.delete(iGoodsDelete.getIds());
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error("/bonsai/goods/delete error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，删除失败");
        }
    }


    /**
     * 更新商品
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse update(@RequestBody Goods goods){
        if (goods == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "商品的参数不能为空");
        }
        Goods temp = goodsService.selectByPrimaryKey(goods.getId());
        if (temp == null){
            return new ApiResponse(ApiResponse.CODE_NOT_EXIST, "商品不存在");
        }
        try {
            goodsService.update(goods);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "更新成功");
        } catch (Exception e) {
            logger.error("/bonsai/goods/update error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，更新失败");
        }
    }

    /**得到某个商品的信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ApiResponse get(@PathVariable("id") int id){
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取商品信息成功",goodsService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("/bonsai/goods/get/{id} error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，获取商品信息失败");
        }
    }


    /**
     * 得到所有商品的信息（这个商品的信息用elasticsearch比较好，暂时用这后面会被替换）
     * @param iGoodsList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse list(@RequestBody IGoodsList iGoodsList){
        // https://www.cnblogs.com/xiaoxinwt/p/5329840.html Mybatis 的分页插件PageHelper-4.1.1的使用
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取所有商品信息成功", goodsService.selectAllGoods(iGoodsList));
        } catch (Exception e) {
            logger.error("/list"+" error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "获取所有商品信息失败");
        }
    }
}
