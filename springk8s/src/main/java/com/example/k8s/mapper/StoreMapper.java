package com.example.k8s.mapper;

import com.example.k8s.model.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

    Store selectByStorename(String storename);

    List<Store> selectAllStore();
}