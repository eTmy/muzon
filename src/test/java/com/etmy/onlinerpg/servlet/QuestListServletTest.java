package com.etmy.onlinerpg.servlet;

import java.io.IOException;

import com.etmy.onlinerpg.core.Account;
import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.core.User;

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


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestListServletTest {
    private static final String LOGIN = "Tester";

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    Application application;
    @Mock
    GameSession gameSession;
    @Spy
    QuestListServlet servlet;


    @Test
    void testDoGet_WhenMethodInvoked_ShouldSetResponseBody() throws ServletException, IOException {
        when(application.getGameSession(LOGIN))
                .thenReturn(gameSession);
        when(gameSession.getUser())
                .thenReturn(new User(new Account()));

        try (MockedStatic<ServletUtils> servletUtils = Mockito.mockStatic(ServletUtils.class)) {
            servletUtils.when(() -> ServletUtils.extractApp(request))
                    .thenReturn(application);
            servletUtils.when(() -> ServletUtils.extractLogin(request))
                    .thenReturn(LOGIN);

            servlet.doGet(request, response);

            servletUtils.verify(() -> ServletUtils.setResponseBody(any(HttpServletResponse.class), anyString()));

        }
    }
}