package com.etmy.onlinerpg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSession {
    private Player player;

    {
        this.player = new Player();
    }
}
