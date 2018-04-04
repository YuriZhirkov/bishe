package com.example.k8s.service;

import com.example.k8s.dto.IGoodsList;
import com.example.k8s.model.Goods;
import com.example.k8s.untils.PageResult;

/**
 * Created by zzg on 2018/1/1.
 */
public interface GoodsService {

    int add(Goods goods);

    Goods selectByGoodsname(String goodsname);

    void delete(Integer[] ids);

    void update(Goods goods);

    Goods selectByPrimaryKey(Integer id);

    PageResult<Goods> selectAllGoods(IGoodsList iGoodsList);

    PageResult<Goods> search(IGoodsList iGoodsList);

}
