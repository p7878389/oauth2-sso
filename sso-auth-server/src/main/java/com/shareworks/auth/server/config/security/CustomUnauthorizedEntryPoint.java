package com.shareworks.auth.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shareworks.auth.server.config.SsoProperties;
import com.shareworks.auth.server.dto.ResponseDTO;
import com.shareworks.auth.server.enums.GlobalErrorCodeEnums;
import com.shareworks.auth.server.utils.AjaxUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/9 14:43
 */
@Component("customUnauthorizedEntryPoint")
@AllArgsConstructor
public class CustomUnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    private final SsoProperties ssoProperties;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map<String, String[]> paramMap = request.getParameterMap();
        String param = paramMap.entrySet()
                .stream()
                .filter(entry -> Objects.nonNull(entry.getValue()))
                .map(entry -> entry.getKey() + "=" + entry.getValue()[0])
                .collect(Collectors.joining("&"));
        String isRedirectValue = request.getParameter("isRedirect");
        if (!StringUtils.isEmpty(isRedirectValue) && Boolean.valueOf(isRedirectValue)) {
            response.sendRedirect(ssoProperties.getFrontEndDomain() + "/login");
            return;
        }

        String authUrl = ssoProperties.getFrontEndDomain() + "/oauth/authorize?" + param + "&isRedirect=true";

        if (!AjaxUtils.isAjax(request)) {
            response.sendRedirect(authUrl);
            return;
        }

        ResponseDTO<String> responseDTO = GlobalErrorCodeEnums.USER_NOT_LOGIN.convert(authUrl);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.print(objectMapper.writeValueAsString(responseDTO));
        writer.flush();
        writer.close();
    }
}
