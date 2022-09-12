package com.etmy.onlinerpg.core;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Setter
@Getter
public class Application {
    private Map<String, GameSession> gameSessions = new ConcurrentHashMap<>();

    public GameSession getGameSession(String login) {
        return gameSessions.get(login);
    }

    public boolean isAuthorized(String login) {
        return gameSessions.containsKey(login);
    }
}
