package com.local.demo.service.impl;

import com.local.demo.entity.UserInfo;
import com.local.demo.service.MasterUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 主库用户服务impl
 *
 * @author echo
 * @date 2024/01/12
 */
@Service("masterUserService")
@Slf4j
public class MasterUserServiceImpl implements MasterUserService {
    @Override
    public UserInfo queryUserInfoById(Long id) {
        return new UserInfo();
    }
}
