package com.example.k8s.mapper;

import com.example.k8s.model.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> selectByGoodsname(Map<String, String> map);

    List<Orders> selectBySellid(Integer sellid);

    List<Orders> selectByBuyid(Integer buyid);

}