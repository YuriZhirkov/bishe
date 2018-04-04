package com.example.k8s.service;

import com.example.k8s.model.Goods;
import com.example.k8s.untils.PageResult;

import java.util.List;

/**
 * Created by zzg on 2018/4/4.
 */
public interface MySearchService {

    //增加产品
    String index(List<Object> objs,String indexName,String typeName) throws Exception;

    //删除产品
    void delete(String indexName,String typeName,String id) throws Exception;

    //修改产品
    void update(String indexName,String typeName,Goods goods) throws Exception;

    //查询数据
    PageResult<Goods> qurey(String indexName, String typeName, String keyword) throws Exception;

}
