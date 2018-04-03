package com.example.k8s.controller;

import com.example.k8s.dto.ICommentList;
import com.example.k8s.model.Comment;
import com.example.k8s.service.CommentService;
import com.example.k8s.untils.ApiResponse;
import com.example.k8s.untils.SimpleApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zzg on 2018/3/29.
 */
@Controller
@RequestMapping(value = "/bonsai/comment")
@CrossOrigin
public class CommentController {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论/留言
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse add(@RequestBody Comment comment){
        if (comment == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "添加评论/留言", commentService.add(comment));
        } catch (Exception e) {
            logger.error("/bonsai/comment/add error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，添加评论/留言失败");
        }
    }


    /**
     * 删除评论/留言
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse delete(@PathVariable Integer id){
        try {
            commentService.delete(id);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error("/bonsai/comment/delete error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，删除失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse list(@RequestBody ICommentList iCommentList){
        // https://www.cnblogs.com/xiaoxinwt/p/5329840.html Mybatis 的分页插件PageHelper-4.1.1的使用
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取所有评论/留言信息成功", commentService.list(iCommentList));
        } catch (Exception e) {
            logger.error("/bonsai/comment/list"+" error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "获取所有评论/留言信息失败");
        }
    }
}
