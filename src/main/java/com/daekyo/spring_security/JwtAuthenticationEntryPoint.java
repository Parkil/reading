package com.daekyo.spring_security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    해당 클래스의 역할은 spring security 예외 발생 시 예외를 전달해 주는 역할
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e) throws IOException {

        logger.info("JwtAuthenticationEntryPoint commence called");

        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());

//        ErrorCode unAuthorizationCode = (ErrorCode) request.getAttribute("unauthorization.code");
//
//        request.setAttribute("response.failure.code", unAuthorizationCode.name());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, unAuthorizationCode.message());

        request.setAttribute("response.failure.code", "401");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
    }
}