package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.*;
import com.etmy.onlinerpg.location.Hotel;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestServletTest {
    private static final String LOGIN = "Tester";
    private static final String NPC_NAME = "Jake";

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    Application application;
    @Mock
    GameSession gameSession;
    @Mock
    Quest quest;
    @Spy
    QuestServlet servlet;

    @Test
    void testDoGet_WhenQuestIdIsPresent_ShouldAddQuestToUser() throws ServletException, IOException {
        User user = new User(new Account());
        user.setCurrentLocation(new Hotel());

        try (MockedStatic<ServletUtils> servletUtils = Mockito.mockStatic(ServletUtils.class)) {
            servletUtils.when(() -> ServletUtils.extractApp(request))
                    .thenReturn(application);
            servletUtils.when(() -> ServletUtils.extractLogin(request))
                    .thenReturn(LOGIN);

            when(application.getGameSession(LOGIN))
                    .thenReturn(gameSession);
            when(gameSession.getUser())
                    .thenReturn(user);

            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "npcName"))
                    .thenReturn(NPC_NAME);
            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "questId"))
                    .thenReturn("1");
            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "isAccepted"))
                    .thenReturn("true");
            servletUtils.when(() -> ServletUtils.getRequestParameter(request, "isFinished"))
                    .thenReturn("true");

            servlet.doGet(request, response);

            assertTrue(user.getQuests().size() > 0);
        }
    }
}