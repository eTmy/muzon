package com.etmy.onlinerpg.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSession {
    private User user;

    public GameSession(User user) {
        this.user = user;
    }
}
