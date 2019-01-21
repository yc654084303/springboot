package com.osdevs.testspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String userId;
    private String userName;
    private String passWord;
    private String mobile;
    private String email;
    private String userUrl;
    private UserToken token;
    /**
     * 1手机用户，2，邮件用户，3微信用户，4QQ用户，5web用户
     */
    @ApiParam(required=true,name="用户类型",value ="1手机用户，2，邮件用户，3微信用户，4QQ用户，5web用户")
    private int userType;
//    private DeviceType deviceType;
    @JsonIgnore
    private Date registerTime;

    public User() {

    }

    public User( String userId, String userName, String passWord, String mobile, String email, int userType, Date registerTime) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.mobile = mobile;
        this.email = email;
        this.userUrl = userUrl;
        this.userType = userType;
        this.registerTime = registerTime;
    }

    public UserToken getToken() {
        return token;
    }

    public void setToken(UserToken token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}
