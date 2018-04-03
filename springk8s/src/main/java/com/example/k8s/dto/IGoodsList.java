package com.example.k8s.dto;

import java.io.Serializable;

/**
 * Created by zzg on 2018/1/6.
 */
public class IGoodsList implements Serializable {
    private static final long serialVersionUID = 3098678905674934207L;
    private int pageNum;
    private int pageSize;
    private Integer userId;//卖家的id

    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
