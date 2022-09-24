package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Application;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/auth.html", "/start", "/image/auth.png", "/js/script.js")));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Application app = ServletUtils.extractApp(request);

        String login = ServletUtils.extractLogin(request);
        String path = request.getServletPath();

        boolean isLogged = ServletUtils.sessionIsAuthorized(app, login);

        if (isLogged || ALLOWED_PATHS.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendError(401);
        }

    }
}
