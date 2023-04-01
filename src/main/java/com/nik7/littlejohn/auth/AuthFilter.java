package com.nik7.littlejohn.auth;

import com.google.common.base.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
public class AuthFilter extends OncePerRequestFilter {
    public static final String USER_AUTH = "userAuth";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userName = getUserName(request, response);
        if (userName == null) {
            return;
        }
        request.setAttribute(USER_AUTH, userName);
        filterChain.doFilter(request, response);
    }

    private String getUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorization = request.getHeader("Authorization");

        if (Strings.isNullOrEmpty(authorization)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "No authorization found, please login!");
            return null;
        }

        if (!authorization.matches("^Basic .+$")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid authorization found, please login again!");
            return null;
        }

        String[] elements = authorization.split("Basic ");

        String basicAuth = elements[1];

        String userInfo = new String(Base64.getDecoder().decode(basicAuth.getBytes()));

        if (!userInfo.matches("^.+:.*$")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid authorization found, please login again!");
            return null;
        }

        return userInfo.split(":")[0];
    }
}
