package com.shareworks.auth.server.config.oauth;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/10 14:22
 */
@Component
@AllArgsConstructor
public class CustomAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    private static final String prefix = "rms:authorization_code:";

    private final RedisTemplate<String, Object> redisTemplate;

    private final JdkSerializationStrategy serializationStrategy = new JdkSerializationStrategy();

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        String key = prefix + code;
        redisTemplate.opsForValue().set(key, serializationStrategy.serialize(authentication), 10L, TimeUnit.MINUTES);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        Object valueObj = redisTemplate.opsForValue().get(prefix + code);
        Assert.notNull(valueObj, "authorizationCode 已过期");
        OAuth2Authentication auth2Authentication =
                serializationStrategy.deserialize(
                        (byte[]) valueObj, OAuth2Authentication.class);
        redisTemplate.delete(prefix + code);
        return auth2Authentication;
    }
}
