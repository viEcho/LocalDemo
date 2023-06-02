package com.local.test.threadTest.vo;

import java.io.Serializable;

/**
 * @description: 用户信息类
 * @author: echo
 * @date: 2023/2/18
 */
public class UserInfo implements Serializable {

    private Long userId;

    private String userName;

    private String sex;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



}
