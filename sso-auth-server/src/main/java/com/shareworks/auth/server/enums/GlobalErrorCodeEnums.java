package com.shareworks.auth.server.enums;

import com.shareworks.auth.server.dto.ResponseDTO;
import lombok.Getter;

@Getter
public enum GlobalErrorCodeEnums {
    FORBIDDEN("403", "禁止访问"),
    OK("200","成功"),
    LOGIN_FAILURE("400","登录失败"),
    USER_NOT_LOGIN("U100001","请先登录")
    ;

    private final String code;
    private final String message;

    GlobalErrorCodeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public <T> ResponseDTO<T> convert() {
        return new ResponseDTO<T>(getCode(), getMessage(), null);
    }
}
