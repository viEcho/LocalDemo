package com.local.demo.service.impl;

import com.local.demo.entity.UserInfo;
import com.local.demo.service.SlaveUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 从库用户服务impl
 *
 * @author echo
 * @date 2024/01/12
 */
@Service("slaveUserService")
@Slf4j
public class SlaveUserServiceImpl implements SlaveUserService {
    @Override
    public UserInfo queryUserInfoById(Long id) {
        return new UserInfo();
    }
}
