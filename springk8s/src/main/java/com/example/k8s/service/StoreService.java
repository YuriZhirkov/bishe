package com.example.k8s.service;

import com.example.k8s.model.Store;
import com.example.k8s.untils.PageResult;

/**
 * Created by zzg on 2018/1/2.
 */
public interface StoreService {

    int add(Store store);

    Store selectByStorename(String storename);

    void delete(Integer[] ids);

    void update(Store store);

    Store selectByPrimaryKey(Integer id);

    PageResult<Store> selectAllStore(int pageNum, int pageSize);
}
