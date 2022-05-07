package com.shareworks.auth.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shareworks.auth.server.domain.ApplicationInfo;import org.apache.ibatis.annotations.Param;

public interface ApplicationInfoMapper extends BaseMapper<ApplicationInfo> {
    ApplicationInfo findByAppId(@Param("appId") String appId);
}