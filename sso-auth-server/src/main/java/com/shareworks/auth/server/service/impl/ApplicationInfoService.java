package com.shareworks.auth.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shareworks.auth.server.domain.ApplicationInfo;
import com.shareworks.auth.server.mapper.ApplicationInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class ApplicationInfoService extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> {

    public ApplicationInfo findByAppId(String appId) {
        return getBaseMapper().findByAppId(appId);
    }
}

