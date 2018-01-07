package com.example.k8s.model;

public class Store {
    private Integer id;

    private String storename;

    private String storedesc;

    private String storepicture;

    private Long storetime;

    private String username ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public String getStoredesc() {
        return storedesc;
    }

    public void setStoredesc(String storedesc) {
        this.storedesc = storedesc == null ? null : storedesc.trim();
    }

    public String getStorepicture() {
        return storepicture;
    }

    public void setStorepicture(String storepicture) {
        this.storepicture = storepicture == null ? null : storepicture.trim();
    }

    public Long getStoretime() {
        return storetime;
    }

    public void setStoretime(Long storetime) {
        this.storetime = storetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}