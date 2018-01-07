package com.example.k8s.dto;

/**
 * Created by zzg on 2018/1/2.
 */
public class INewsDelete {

    private static final long serialVersionUID = 3094444265674934207L;

    private Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
