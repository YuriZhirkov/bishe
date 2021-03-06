package com.example.k8s.mapper;

import com.example.k8s.model.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    Goods selectByGoodsname(String goodsname);

    List<Goods> selectAllGoods();

    List<Goods> search(String condition);

    List<Goods> selectAllGoodsByUserid(Integer userId);
}