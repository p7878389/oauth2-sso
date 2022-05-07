package com.shareworks.auth.server.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/6 17:31
 */
public class AjaxUtils {

    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request
                .getHeader("X-Requested-With")) || "apiLogin".equals(request
                .getHeader("api-login"));
    }
}
