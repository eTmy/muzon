package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Account;
import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.core.User;
import com.etmy.onlinerpg.location.Hotel;
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

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DialogServletTest {
    private static final String LOGIN = "Tester";
    private static final String NPC_NAME = "Jake";
    private User user;
    private GameSession gameSession;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Application application;
    @Spy
    private DialogServlet servlet;

    @BeforeEach
    void setup() {
        user = new User(new Account());
        gameSession = new GameSession(user);
    }

    @Test
    void testDoGet_WhenMessageIdIs0_ShouldReturn() throws ServletException, IOException {
        try (MockedStatic<ServletUtils> servletUtils = Mockito.mockStatic(ServletUtils.class)) {
            servletUtils.when(() -> ServletUtils.extractApp(request))
                    .thenReturn(application);
            servletUtils.when(() -> ServletUtils.extractLogin(request))
                    .thenReturn(LOGIN);
            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "npcName"))
                    .thenReturn(NPC_NAME);
            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "messageId"))
                    .thenReturn("0");

            servlet.doGet(request, response);

            servletUtils.verify(() -> ServletUtils.extractApp(request));
            servletUtils.verify(() -> ServletUtils.extractLogin(request));
            servletUtils.verify(() -> ServletUtils.getRequestParameter(request, "npcName"));
            servletUtils.verify(() -> ServletUtils.getRequestParameter(request, "messageId"));
        }
    }

    @Test
    void testDoGet_WhenCreatureEmpty_ShouldSendError500() throws ServletException, IOException {
        try (MockedStatic<ServletUtils> servletUtils = Mockito.mockStatic(ServletUtils.class)) {
            servletUtils.when(() -> ServletUtils.extractApp(request))
                    .thenReturn(application);
            servletUtils.when(() -> ServletUtils.extractLogin(request))
                    .thenReturn(LOGIN);
            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "npcName"))
                    .thenReturn(NPC_NAME);
            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "messageId"))
                    .thenReturn("1");

            when(application.getGameSession(LOGIN))
                    .thenReturn(gameSession);

            servlet.doGet(request, response);

            verify(response).sendError(500, "Not found npc " + NPC_NAME);
        }
    }

    @Test
    void testDoGet_WhenCreatureIsPresent_ShouldSetResponseBody() throws ServletException, IOException {
        user.setCurrentLocation(new Hotel());

        try (MockedStatic<ServletUtils> servletUtils = Mockito.mockStatic(ServletUtils.class)) {
            servletUtils.when(() -> ServletUtils.extractApp(request))
                    .thenReturn(application);
            servletUtils.when(() -> ServletUtils.extractLogin(request))
                    .thenReturn(LOGIN);
            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "npcName"))
                    .thenReturn(NPC_NAME);
            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "messageId"))
                    .thenReturn("1");

            when(application.getGameSession(LOGIN))
                    .thenReturn(gameSession);

            servlet.doGet(request, response);

            servletUtils.verify(() -> ServletUtils.setResponseBody(any(HttpServletResponse.class), anyString()));
        }
    }


}