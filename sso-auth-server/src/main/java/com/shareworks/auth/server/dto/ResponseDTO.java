package com.shareworks.auth.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private String code;
    private String message;
    private T data;

    public boolean success() {
        return "00000".equals(code);
    }
}
