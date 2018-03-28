package com.example.k8s.dto;

/**
 * Created by zzg on 2018/1/1.
 */
public class IOrdersDelete {

    private static final long serialVersionUID = 3098144265674909807L;

    private String[] ids;


    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
