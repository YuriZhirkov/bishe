package com.example.k8s.controller;

import com.example.k8s.model.User;
import com.example.k8s.service.UserService;
import com.example.k8s.untils.ApiResponse;
import com.example.k8s.untils.SimpleApiResponse;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/12/24 0024.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse addUser(@RequestBody User user){
        if (user == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "");
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "", userService.addUser(user));
        } catch (Exception e) {
            logger.error("//user/add error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "");
        }
    }

    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        // https://www.cnblogs.com/xiaoxinwt/p/5329840.html Mybatis 的分页插件PageHelper-4.1.1的使用
        PageHelper.startPage(pageNum,pageSize);
        return userService.findAllUser(pageNum,pageSize);
    }
}
