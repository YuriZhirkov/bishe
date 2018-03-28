package com.example.k8s.dto;

import java.io.Serializable;

/**
 * Created by zzg on 2018/1/6.
 */
public class IOrderList implements Serializable {
    private static final long serialVersionUID = 3098678905674931107L;
    private int pageNum;
    private int pageSize;

    private String name;

    private String username;

    private Integer sellid;

    private Integer buyid;

    public Integer getBuyid() {
        return buyid;
    }

    public void setBuyid(Integer buyid) {
        this.buyid = buyid;
    }

    public Integer getSellid() {
        return sellid;
    }

    public void setSellid(Integer sellid) {
        this.sellid = sellid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
