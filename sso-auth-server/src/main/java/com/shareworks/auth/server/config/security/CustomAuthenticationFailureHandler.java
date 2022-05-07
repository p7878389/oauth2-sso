package com.shareworks.auth.server.config.security;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shareworks.auth.server.dto.ResponseDTO;
import com.shareworks.auth.server.enums.GlobalErrorCodeEnums;
import com.shareworks.auth.server.utils.AjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/6 17:29
 */
@Slf4j
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private String failureUrl = "/signIn";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        if (AjaxUtils.isAjax(request)) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                ResponseDTO<Object> responseMessage = GlobalErrorCodeEnums.LOGIN_FAILURE.convert();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(response.getOutputStream(),
                        JsonEncoding.UTF8);
                objectMapper.writeValue(jsonGenerator, responseMessage);
            } catch (Exception ex) {
                if (log.isErrorEnabled()) {
                    log.error("Could not write JSON:", ex);
                }
                throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
            }
        } else {
            String encodedMessage = "";
            try {
                encodedMessage = URLEncoder.encode(exception.getMessage(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                if (log.isErrorEnabled()) {
                    log.error("encodedMessage", e);
                }
            }
            response.sendRedirect(failureUrl + "?authentication_error=true&error=" + encodedMessage);
        }
    }
}
