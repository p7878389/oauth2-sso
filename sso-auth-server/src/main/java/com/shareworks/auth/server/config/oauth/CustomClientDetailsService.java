package com.shareworks.auth.server.config.oauth;

import com.shareworks.auth.server.domain.ApplicationInfo;
import com.shareworks.auth.server.service.impl.ApplicationInfoService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/7 10:28
 */
@Component
@AllArgsConstructor
public class CustomClientDetailsService implements ClientDetailsService {

    private final ApplicationInfoService applicationInfoService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ApplicationInfo applicationInfo = applicationInfoService.findByAppId(clientId);
        Assert.notNull(applicationInfo, "clientId: {" + clientId + "} not found");

        CustomClientDetails clientDetails = new CustomClientDetails();
        clientDetails.setClientId(applicationInfo.getAppId());
        clientDetails.setClientSecret(passwordEncoder.encode(applicationInfo.getAppSecret()));
        clientDetails.setAuthorizedGrantTypes(Arrays.stream(applicationInfo.getGrantTypes().split(",")).collect(Collectors.toSet()));
        clientDetails.setAutoApproveScopes(Collections.singleton("all"));
        clientDetails.setScope(Collections.singleton("all"));
        clientDetails.setRegisteredRedirectUri(Collections.singleton(applicationInfo.getRedirectUri()));
        clientDetails.setAccessTokenValiditySeconds(60 * 60 * 2);
        clientDetails.setRefreshTokenValiditySeconds(60 * 60 * 2);
        return clientDetails;
    }
}
