package com.osdevs.testspringboot.entity;

import java.util.Date;

public class UserToken {
    public final static long TOKEN_REFRESH_TIME=1000 * 60 *5;
    private Date buildTime;
    private String token;
    private String userId;

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
