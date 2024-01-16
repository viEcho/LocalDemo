package com.local.demo.service;


import com.local.demo.entity.UserInfo;

public interface SlaveUserService {
    UserInfo queryUserInfoById(Long id);
}
