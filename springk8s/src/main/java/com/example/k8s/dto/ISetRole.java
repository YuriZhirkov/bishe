package com.example.k8s.dto;

import java.io.Serializable;

/**
 * Created by zzg on 2018/1/1.
 */
public class ISetRole implements Serializable {

    private static final long serialVersionUID = 3098144267735980507L;

    private Integer id;

    private String flagrole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlagrole() {
        return flagrole;
    }

    public void setFlagrole(String flagrole) {
        this.flagrole = flagrole;
    }
}
