package com.etmy.onlinerpg.core;

import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.location.Hotel;
import com.etmy.onlinerpg.abstraction.Location;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User extends Creature {
    private Location currentLocation;
    private Account account;

    {
        //подгружать из настроек сохраненную комнату
        this.currentLocation = new Hotel();
    }

    public User(Account account) {
        this.account = account;
    }
}
