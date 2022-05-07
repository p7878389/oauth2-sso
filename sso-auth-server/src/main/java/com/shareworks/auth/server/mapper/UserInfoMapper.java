package com.shareworks.auth.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shareworks.auth.server.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserInfo findByUserName(@Param("userName") String userName);
}
