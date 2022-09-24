package com.etmy.onlinerpg.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

    private final Application application = new Application();
    @Mock
    private GameSession gameSession;

    @BeforeEach
    void setup() {
        application.getGameSessions().put("Tester", gameSession);
    }

    @Test
    public void testIsAuthorized_WhenLoginIsAuthorized_ShouldReturnTrue() {
        assertTrue(application.isAuthorized("Tester"));
    }

    @Test
    void testIsAuthorized_WhenLoginNotAuthorized_ShouldReturnFalse() {
        assertFalse(application.isAuthorized("noname"));
    }

    @Test
    void testIsAuthorized_WhenLoginHaveGameSession_ShouldReturnTrue() {
        assertEquals(gameSession, application.getGameSession("Tester"));
    }

    @Test
    void testIsAuthorized_WhenLoginNotHaveGameSession_ShouldReturnNull() {
        assertNull(application.getGameSession("noname"));
    }


}