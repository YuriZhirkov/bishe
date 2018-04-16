package com.example.k8s.mapper;

import com.example.k8s.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    //通过产品的id查询评论
    List<Comment> selectByGoodsId(Integer goodsid);
    //通过sellid的查询留言
    List<Comment> selectBySellId(Integer sellid);

    //通过buyid的查询留言
    List<Comment>  selectByBuyid(Integer userid);
}