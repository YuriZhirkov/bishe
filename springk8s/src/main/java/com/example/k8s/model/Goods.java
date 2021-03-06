package com.example.k8s.model;

import io.searchbox.annotations.JestId;

public class Goods {

    @JestId
    private Integer id;

    private String goodsname;

    private String detail;

    private String price;

    private String pictures;

    private String storage;

    private String soldnumber;

    private Integer userid;

    private String type;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures == null ? null : pictures.trim();
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage == null ? null : storage.trim();
    }

    public String getSoldnumber() {
        return soldnumber;
    }

    public void setSoldnumber(String soldnumber) {
        this.soldnumber = soldnumber == null ? null : soldnumber.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Goods(Integer id, String goodsname, String detail, String price, String pictures, String storage, String soldnumber, Integer userid, String type) {
        this.id = id;
        this.goodsname = goodsname;
        this.detail = detail;
        this.price = price;
        this.pictures = pictures;
        this.storage = storage;
        this.soldnumber = soldnumber;
        this.userid = userid;
        this.type = type;
    }

    public Goods() {
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsname='" + goodsname + '\'' +
                ", detail='" + detail + '\'' +
                ", price='" + price + '\'' +
                ", pictures='" + pictures + '\'' +
                ", storage='" + storage + '\'' +
                ", soldnumber='" + soldnumber + '\'' +
                ", userid=" + userid +
                ", type='" + type + '\'' +
                '}';
    }
}