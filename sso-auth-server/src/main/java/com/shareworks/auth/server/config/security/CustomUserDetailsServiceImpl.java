package com.shareworks.auth.server.config.security;

import com.shareworks.auth.server.domain.UserInfo;
import com.shareworks.auth.server.enums.UserStatusEnums;
import com.shareworks.auth.server.service.impl.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/6 17:47
 */
@Component
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoService userInfoService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoService.findByUserName(username);
        if (null == userInfo) {
            log.error("用户{}不存在", username);
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(userInfo.getUserName()
                , passwordEncoder.encode(userInfo.getPassword())
                , UserStatusEnums.ENABLE.equals(userInfo.getStatus())
                , !UserStatusEnums.EXPIRED.equals(userInfo.getStatus())
                , true
                , !UserStatusEnums.LOCK.equals(userInfo.getStatus())
                , Collections.emptyList());
    }
}
