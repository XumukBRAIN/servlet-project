package com.dev.servlet.services;

import com.dev.servlet.configs.InstanceConfiguration;
import com.dev.servlet.repositories.SecurityRepository;
import com.dev.servlet.security.UserInfo;

public class SecurityService {

    private final SecurityRepository securityRepository = InstanceConfiguration.getSecurityRepositoryInstance();

    public void register(UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("userInfo must not be null");
        }

        securityRepository.register(userInfo);
    }
}
