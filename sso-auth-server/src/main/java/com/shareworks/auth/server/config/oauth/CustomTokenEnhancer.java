package com.shareworks.auth.server.config.oauth;

import com.shareworks.auth.server.config.security.CustomUserDetails;
import com.shareworks.auth.server.constant.AuthConstants;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/10 10:10
 */
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> map = new HashMap<>(2);
        CustomUserDetails user = (CustomUserDetails) authentication.getUserAuthentication().getPrincipal();
        map.put(AuthConstants.JWT_USER_ID_KEY, user.getUserId());
        map.put(AuthConstants.JWT_USER_NAME_KEY, user.getUsername());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        return accessToken;
    }
}
