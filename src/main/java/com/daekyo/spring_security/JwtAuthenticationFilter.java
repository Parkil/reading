package com.daekyo.spring_security;

import com.daekyo.exception.JWTException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /*
        jwt token이 없을 경우 오류가 발생하는건 맞지만 authorizeRequests에서 authenticated/permitAll 설정에 따라서
        exceptionHandling 으로 넘어가느냐 마느냐가 결정되는 듯
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwtToken = getJwtFromRequest(request);
            JwtTokenProvider.validateToken(jwtToken);
        } catch (JWTException ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = Optional.ofNullable(request.getHeader("Authorization")).orElse("");

        Matcher matcher = Pattern.compile("^(Bearer )(.*)$").matcher(bearerToken);

        if(matcher.find()) {
            return matcher.group(2);
        }
        return "";
    }
}