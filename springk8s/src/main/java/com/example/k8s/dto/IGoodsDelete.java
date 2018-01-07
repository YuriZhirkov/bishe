package com.example.k8s.dto;

import java.io.Serializable;

/**
 * Created by zzg on 2018/1/1.
 */
public class IGoodsDelete implements Serializable {
    private static final long serialVersionUID = 3098144265674934207L;

    private Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
