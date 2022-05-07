package com.shareworks.auth.server.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/6 17:52
 */
@AllArgsConstructor
@Getter
public enum UserStatusEnums {

    LOCK("lock", "锁定"),
    EXPIRED("expired", "失效"),
    ENABLE("enable", "正常");

    @EnumValue
    @JsonValue
    private final String status;
    private final String desc;

}
