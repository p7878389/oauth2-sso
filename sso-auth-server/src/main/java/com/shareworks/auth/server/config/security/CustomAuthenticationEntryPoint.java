//package com.shareworks.auth.server.config.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.shareworks.auth.server.dto.ResponseDTO;
//import com.shareworks.auth.server.enums.GlobalErrorCodeEnums;
//import com.shareworks.auth.server.utils.AjaxUtils;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author martin.peng
// * @Desc
// * @date 2022/5/7 9:21
// */
//@Component
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        ResponseDTO<Object> responseDTO = GlobalErrorCodeEnums.USER_NOT_LOGIN.convert();
//        if (AjaxUtils.isAjax(request)) {
//            response.setContentType("text/json;charset=utf-8");
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(response.getWriter(), responseDTO);
//            return;
//        }
//        response.sendRedirect("/login");
//    }
//}
