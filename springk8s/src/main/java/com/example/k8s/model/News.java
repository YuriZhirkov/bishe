package com.example.k8s.model;

public class News {
    private Integer id;

    private String newstitle;

    private String newscontent;

    private String newspicture;

    private Long newtime;

    private String newspush;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle == null ? null : newstitle.trim();
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent == null ? null : newscontent.trim();
    }

    public String getNewspicture() {
        return newspicture;
    }

    public void setNewspicture(String newspicture) {
        this.newspicture = newspicture == null ? null : newspicture.trim();
    }

    public Long getNewtime() {
        return newtime;
    }

    public void setNewtime(Long newtime) {
        this.newtime = newtime;
    }

    public String getNewspush() {
        return newspush;
    }

    public void setNewspush(String newspush) {
        this.newspush = newspush == null ? null : newspush.trim();
    }
}