package com.example.k8s.model;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String cnname;

    private String email;

    private String mobile;

    private Long createtime;

    private Long updatetime;

    private Long lastlogintime;

    private String state;

    private String headurl;

    private String address;

    private String flagrole;

    private Integer telephone; //电话

    private String postalcode;

    private Integer initialmoney;

    private Integer surplusmoney;

    public Integer getInitialmoney() {
        return initialmoney;
    }

    public void setInitialmoney(Integer initialmoney) {
        this.initialmoney = initialmoney;
    }

    public Integer getSurplusmoney() {
        return surplusmoney;
    }

    public void setSurplusmoney(Integer surplusmoney) {
        this.surplusmoney = surplusmoney;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname == null ? null : cnname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public Long getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Long lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl == null ? null : headurl.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getFlagrole() {
        return flagrole;
    }

    public void setFlagrole(String flagrole) {
        this.flagrole = flagrole == null ? null : flagrole.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cnname='" + cnname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", lastlogintime=" + lastlogintime +
                ", state='" + state + '\'' +
                ", headurl='" + headurl + '\'' +
                ", address='" + address + '\'' +
                ", flagrole='" + flagrole + '\'' +
                ", telephone=" + telephone +
                ", postalcode='" + postalcode + '\'' +
                '}';
    }
}