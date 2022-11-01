package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.abstraction.Location;
import com.etmy.onlinerpg.core.Account;
import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.core.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InitServletTest {
    private static final String LOGIN = "Tester";
    private static final String JSON_STRING = "{\"login\":\"Tester\", \"password\":\"test\"}";
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession httpSession;
    @Mock
    Application application;
    @Mock
    GameSession gameSession;
    @Mock
    User user;
    @Mock
    Account account;
    @Mock
    Location currentLocation;
    @Spy
    InitServlet servlet;

    @BeforeEach
    void setup() {
        when(request.getSession(true))
                .thenReturn(httpSession);
    }

    @Test
    void testDoPost_WhenSessionIsAuthorized_ShouldSendRedirect() throws ServletException, IOException {
        when(application.getGameSession(LOGIN))
                .thenReturn(gameSession);
        when(gameSession.getUser())
                .thenReturn(user);
        when(user.getAccount())
                .thenReturn(account);
        when(user.getCurrentLocation())
                .thenReturn(currentLocation);
        when(account.getLogin())
                .thenReturn(LOGIN);

        try (MockedStatic<ServletUtils> servletUtils = Mockito.mockStatic(ServletUtils.class)) {
            servletUtils.when(() -> ServletUtils.extractApp(request))
                    .thenReturn(application);
            servletUtils.when(() -> ServletUtils.extractLogin(request))
                    .thenReturn(LOGIN);
            servletUtils.when(() -> ServletUtils.sessionIsAuthorized(application, LOGIN))
                    .thenReturn(true);

            servlet.doPost(request, response);

            verify(response).sendRedirect(argThat("/onlinerpg/index.html"::equals));
        }
    }

    @Test
    void testDoPost_WhenSessionIsNotAuthorized_ShouldSendRedirect() throws ServletException, IOException {
        try (MockedStatic<ServletUtils> servletUtils = Mockito.mockStatic(ServletUtils.class)) {
            servletUtils.when(() -> ServletUtils.extractApp(request))
                    .thenReturn(application);
            servletUtils.when(() -> ServletUtils.extractLogin(request))
                    .thenReturn(LOGIN);
            servletUtils.when(() -> ServletUtils.sessionIsAuthorized(application, LOGIN))
                    .thenReturn(false);
            servletUtils.when(() -> ServletUtils.getReqBody(request))
                    .thenReturn(JSON_STRING);

            servlet.doPost(request, response);

            verify(response).sendRedirect(argThat("/onlinerpg/index.html"::equals));
        }
    }

}