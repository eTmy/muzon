package com.etmy.onlinerpg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSession {
    private User user;

    {
        this.user = new User();
    }
}
