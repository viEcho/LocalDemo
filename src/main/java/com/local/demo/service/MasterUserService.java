package com.local.demo.service;

import com.local.demo.entity.UserInfo;

public interface MasterUserService {
    UserInfo queryUserInfoById(Long id);
}
