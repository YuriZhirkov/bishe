package com.example.k8s.service.impl;

import com.example.k8s.dto.ICommentList;
import com.example.k8s.mapper.CommentMapper;
import com.example.k8s.model.Comment;
import com.example.k8s.model.Goods;
import com.example.k8s.service.CommentService;
import com.example.k8s.untils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzg on 2018/3/29.
 */
@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int add(Comment comment) {
        commentMapper.insertSelective(comment);
        return comment.getId();
    }

    @Override
    public void delete(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<Comment> list(ICommentList iCommentList) {
        PageResult<Comment> pageResult = new PageResult<>();
        List<Comment> list = new ArrayList<>();

        if (iCommentList.getType() == 0) { //这个是评论
            //这是通过goodsid获取某个产品的所有评论
            if (iCommentList.getGoodsid()!=null && iCommentList.getGoodsid()>0){
                PageHelper.startPage(iCommentList.getPageNum(), iCommentList.getPageSize());
                list = commentMapper.selectByGoodsId(iCommentList.getGoodsid());
            }
            //这是通过buyid获取买家所卖产品的所有评论
            if (iCommentList.getBuyid()!=null && iCommentList.getBuyid()>0){
                PageHelper.startPage(iCommentList.getPageNum(), iCommentList.getPageSize());
                list = commentMapper.selectByBuyid(iCommentList.getBuyid());
            }
        } else { //留言(买家对卖家的评论)
            PageHelper.startPage(iCommentList.getPageNum(), iCommentList.getPageSize());
            list = commentMapper.selectBySellId(iCommentList.getSellid());
        }
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        pageResult.setRecordsTotal(pageInfo.getTotal());
        pageResult.setItems(pageInfo.getList());
        return pageResult;

    }
}
