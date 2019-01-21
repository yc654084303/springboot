package com.osdevs.testspringboot.model;

import io.swagger.annotations.ApiParam;

public class UserModel {
    @ApiParam(required = true, name = "userName", value = "用户名")
    private String userName;
    @ApiParam(required = true, name = "passWord", value = "用户密码")
    private String passWord;

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

    @Override
    public String toString() {
        return "{" +
                "userName:'" + userName + '\'' +
                ", passWord:'" + passWord + '\'' +
                '}';
    }
}
