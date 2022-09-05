package com.etmy.onlinerpg;

import com.etmy.onlinerpg.location.StartRoom;
import com.etmy.onlinerpg.location.Location;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    private Location currentLocation;
    private Account account;
    private int money;

    {
        this.currentLocation = new StartRoom();
        this.money = 1200;
    }
}
