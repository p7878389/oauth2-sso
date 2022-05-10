package com.shareworks.auth.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/9 14:51
 */
@Component
@ConfigurationProperties(prefix = "sso")
@Data
public class SsoProperties {

    /**
     * 前端域名
     */
    private String frontEndDomain;

    /**
     * jwt属性
     */
    private JwtProperties jwt;

    @Data
    static class JwtProperties {
        private String signingKey;
    }
}
