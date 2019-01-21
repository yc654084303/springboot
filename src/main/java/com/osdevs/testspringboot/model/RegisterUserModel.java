package com.osdevs.testspringboot.model;

import io.swagger.annotations.ApiParam;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class RegisterUserModel implements Serializable {
    @ApiParam(required=true,name="userName",value ="用户名")
    private String userName;
    @ApiParam(required=true,name="passWord",value ="用户密码")
    private String passWord;
    @ApiParam(required=true,name="confirmPassWord",value ="确认密码")
    private String confirmPassWord;
    /**
     * 1手机用户，2，邮件用户，3微信用户，4QQ用户，5web用户
     */
    @ApiParam(required=true,name="userType",value ="1手机用户，2，邮件用户，5web用户")
    private int userType;
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

    public String getConfirmPassWord() {
        return confirmPassWord;
    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "{" +
                "userName:'" + userName + '\'' +
                ", passWord:'" + passWord + '\'' +
                ", confirmPassWord:'" + confirmPassWord + '\'' +
                ", userType:" + userType +
                '}';
    }
}
