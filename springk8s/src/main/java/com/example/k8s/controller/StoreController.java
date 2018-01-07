package com.example.k8s.controller;

import com.example.k8s.dto.IGoodsDelete;
import com.example.k8s.model.Store;
import com.example.k8s.service.StoreService;
import com.example.k8s.untils.ApiResponse;
import com.example.k8s.untils.SimpleApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zzg on 2018/1/2.
 */
@Controller
@RequestMapping(value = "/bonsai/store")
public class StoreController {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(StoreController.class);

    @Autowired
    private StoreService storeService;

    /**
     * 添加商店
     * @param store
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse add(@RequestBody Store store){
        if (store == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        Store temp = storeService.selectByStorename(store.getStorename());
        if (temp != null){
            return new ApiResponse(ApiResponse.CODE_EXIST, "商店已经存在");
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "添加商店成功", storeService.add(store));
        } catch (Exception e) {
            logger.error("/bonsai/store/add error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，添加商店失败");
        }
    }


    /**
     * 删除商店
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
            storeService.delete(iGoodsDelete.getIds());
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error("/bonsai/store/delete error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，删除失败");
        }
    }


    /**
     * 更新商店
     * @param store
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse update(@RequestBody Store store){
        if (store == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "商店的参数不能为空");
        }
        Store temp = storeService.selectByPrimaryKey(store.getId());
        if (temp == null){
            return new ApiResponse(ApiResponse.CODE_NOT_EXIST, "商店不存在");
        }
        try {
            storeService.update(store);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "更新成功");
        } catch (Exception e) {
            logger.error("/bonsai/store/update error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，更新失败");
        }
    }

    /**得到某个商店的信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ApiResponse get(@PathVariable("id") int id){
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取商店信息成功",storeService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("/bonsai/store/get/{id} error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，获取商店信息失败");
        }
    }


    /**
     * 得到所有商店的信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list/{pageNum}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ApiResponse list(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        // https://www.cnblogs.com/xiaoxinwt/p/5329840.html Mybatis 的分页插件PageHelper-4.1.1的使用
        if (pageSize == 0){
            pageSize = 5;
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取所有商店信息成功", storeService.selectAllStore(pageNum,pageSize));
        } catch (Exception e) {
            logger.error("/bonsai/store/list/"+pageNum+"/"+pageSize+" error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "获取所有商店信息失败");
        }
    }
}
