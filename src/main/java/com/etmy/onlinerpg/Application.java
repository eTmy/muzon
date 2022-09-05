package com.etmy.onlinerpg;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Setter
@Getter
public class Application {
    private Map<Account, GameSession> gameSessions = new ConcurrentHashMap<>();
}
