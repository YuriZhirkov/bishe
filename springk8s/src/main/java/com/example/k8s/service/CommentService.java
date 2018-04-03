package com.example.k8s.service;

import com.example.k8s.dto.ICommentList;
import com.example.k8s.model.Comment;
import com.example.k8s.untils.PageResult;

/**
 * Created by zzg on 2018/3/29.
 */
public interface CommentService {

    //增加评论/留言
    int add(Comment comment);
    //删除评论/留言
    void delete(Integer id);
    //查询评论/留言
    PageResult<Comment> list(ICommentList iCommentList);//通过goodsid或者sellid查询评论和留言
}
