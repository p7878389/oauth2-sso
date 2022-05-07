package com.shareworks.auth.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shareworks.auth.server.domain.UserInfo;
import com.shareworks.auth.server.mapper.UserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {

    public UserInfo findByUserName(String userName) {
        Assert.isTrue(StringUtils.isNotBlank(userName), "userName不能为空");
        return getBaseMapper().findByUserName(userName);
    }
}
