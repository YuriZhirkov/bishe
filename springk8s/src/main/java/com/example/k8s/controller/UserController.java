package com.example.k8s.controller;

import com.example.k8s.dto.IChangePwd;
import com.example.k8s.dto.ISetRole;
import com.example.k8s.dto.IUserDelete;
import com.example.k8s.dto.IUserLogin;
import com.example.k8s.model.User;
import com.example.k8s.service.UserService;
import com.example.k8s.untils.ApiResponse;
import com.example.k8s.untils.SimpleApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/12/24 0024.
 */
@Controller
@RequestMapping(value = "/bonsai/user")
@CrossOrigin
public class UserController {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse create(@RequestBody User user){
        if (user == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "用户参数不能为空");
        }
        User temp = userService.selectByUsernmae(user.getUsername());
        if (temp != null){
            return new ApiResponse(ApiResponse.CODE_EXIST, "用户已经存在");
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "注册成功", userService.create(user));
        } catch (Exception e) {
            logger.error("/bonsai/user/create error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，注册失败");
        }
    }


    /**
     * 删除用户
     * @param iUserDelete
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse delete(@RequestBody IUserDelete iUserDelete){
        if (iUserDelete == null && iUserDelete.getIds().length < 0) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        try {
            userService.delete(iUserDelete.getIds());
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error("/bonsai/user/delete error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，删除失败");
        }
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse update(@RequestBody User user){
        if (user == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "用户参数不能为空");
        }
        User temp = userService.selectByPrimaryKey(user.getId());
        if (temp == null){
            return new ApiResponse(ApiResponse.CODE_NOT_EXIST, "用户不存在");
        }
        try {
            userService.update(user);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "更新成功");
        } catch (Exception e) {
            logger.error("/bonsai/user/update error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，更新失败");
        }
    }

    /**得到某个用户的信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ApiResponse get(@PathVariable("id") int id){
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取用户信息成功",userService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("/bonsai/user/get/{id} error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，获取用户信息失败");
        }
    }

    /**
     * 得到所有用户的信息
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
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "", userService.findAllUser(pageNum,pageSize));
        } catch (Exception e) {
            logger.error("/list/"+pageNum+"/"+pageSize+" error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "");
        }
    }



    /**
     * 修改用户的密码
     * @param iChangePwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changePwd", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse changePwd(@RequestBody IChangePwd iChangePwd){
        if (iChangePwd == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        User temp = userService.selectByPrimaryKey(iChangePwd.getId());
        if (temp == null){
            return new ApiResponse(ApiResponse.CODE_NOT_EXIST, "用户不存在");
        }

        if (!iChangePwd.getOldPassword().equals(temp.getPassword())) {
            return new ApiResponse(ApiResponse.CODE_OTHER, "旧密码不对");
        }
        temp.setPassword(iChangePwd.getNewPassword());
        try {
            userService.update(temp);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "密码修改成功");
        } catch (Exception e) {
            logger.error("/bonsai/user/changePwd error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，密码修改失败");
        }
    }

    /**
     * 修改用户的角色
     * @param iSetRole
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse role(@RequestBody ISetRole iSetRole){
        if (iSetRole == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        User temp = userService.selectByPrimaryKey(iSetRole.getId());
        if (temp == null){
            return new ApiResponse(ApiResponse.CODE_NOT_EXIST, "用户不存在");
        }

        temp.setFlagrole(iSetRole.getFlagrole());
        try {
            userService.update(temp);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "角色修改成功");
        } catch (Exception e) {
            logger.error("/bonsai/user/role error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，角色修改失败");
        }
    }

    /**
     * 用户登录
     * @param iUserLogin
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse login(@RequestBody IUserLogin iUserLogin){
        if (iUserLogin == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "登录成功",userService.login(iUserLogin.getUsername(),iUserLogin.getPassword()));
        } catch (Exception e) {
            logger.error("/bonsai/user/login error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，登录成功失败");
        }
    }


}
