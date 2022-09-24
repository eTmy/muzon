package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.abstraction.Item;
import com.etmy.onlinerpg.core.*;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServletTest {
    private static final String LOGIN = "Tester";
    private static final String JSON_STRING_ACTION_TAKE = """
            {
               "item":{
                  "name":"Catana",
                  "type":"WEAPON",
                  "description":"Unidentified katana: 20 dmg",
                  "srcImage":"image/weapon/catana.png",
                  "questId":0,
                  "damage":20
               },
               "action":"take"
            }
            """;
    private static final String JSON_STRING_ACTION_USE = """
            {
               "item":{
                  "name":"Catana",
                  "type":"WEAPON",
                  "description":"Unidentified katana: 20 dmg",
                  "srcImage":"image/weapon/catana.png",
                  "questId":0,
                  "damage":20
               },
               "action":"use"
            }
            """;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    User user;
    @Mock
    Application application;
    @Mock
    GameSession gameSession;
    @Spy
    ItemServlet servlet;

    @Test
    void testDoPost_WhenActionTake_ShouldInvokeTakeItem() throws ServletException, IOException {
        try (MockedStatic<ServletUtils> servletUtils = Mockito.mockStatic(ServletUtils.class)) {
            servletUtils.when(() -> ServletUtils.extractApp(request))
                    .thenReturn(application);
            servletUtils.when(() -> ServletUtils.extractLogin(request))
                    .thenReturn(LOGIN);
            servletUtils.when(() -> ServletUtils.getReqBody(request))
                    .thenReturn(JSON_STRING_ACTION_TAKE);

            when(application.getGameSession(LOGIN))
                    .thenReturn(gameSession);
            when(gameSession.getUser())
                    .thenReturn(user);

            servlet.doPost(request, response);

            verify(user).takeItem(any(Item.class));
        }
    }

    @Test
    void testDoPost_WhenActionTake_ShouldInvokeUseItem() throws ServletException, IOException {
        try (MockedStatic<ServletUtils> servletUtils = Mockito.mockStatic(ServletUtils.class)) {
            servletUtils.when(() -> ServletUtils.extractApp(request))
                    .thenReturn(application);
            servletUtils.when(() -> ServletUtils.extractLogin(request))
                    .thenReturn(LOGIN);
            servletUtils.when(() -> ServletUtils.getReqBody(request))
                    .thenReturn(JSON_STRING_ACTION_USE);

            when(application.getGameSession(LOGIN))
                    .thenReturn(gameSession);
            when(gameSession.getUser())
                    .thenReturn(user);

            servlet.doPost(request, response);

            verify(user).useItem(any(Item.class));
        }
    }

}