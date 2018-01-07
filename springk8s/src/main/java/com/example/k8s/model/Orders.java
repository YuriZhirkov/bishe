package com.example.k8s.model;

public class Orders {
    private Integer id;

    private String goodsname;

    private String price;

    private String describe;

    private String pictures;

    private String username;

    private Long goodsnumber;

    private Long date;

    private String shipaddress;

    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures == null ? null : pictures.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getGoodsnumber() {
        return goodsnumber;
    }

    public void setGoodsnumber(Long goodsnumber) {
        this.goodsnumber = goodsnumber;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getShipaddress() {
        return shipaddress;
    }

    public void setShipaddress(String shipaddress) {
        this.shipaddress = shipaddress == null ? null : shipaddress.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}