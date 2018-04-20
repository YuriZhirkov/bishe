package com.example.k8s.dto;

/**
 * Created by zzg on 2018/3/28.
 */
public class IOrderRedis {
    private Integer userId;

    private String goodsid;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }
}
