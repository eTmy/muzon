package com.etmy.muzon;

import com.etmy.muzon.location.HotelRoom;
import com.etmy.muzon.location.Location;
import com.etmy.muzon.status.AlcoholLevel;
import com.etmy.muzon.status.Mood;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Player {
    private Location currentLocation;
    private int money;
    private Mood mood;
    private AlcoholLevel drink;

    public Player() {
        this.currentLocation = new HotelRoom();
        this.money = 1200;
        this.mood = Mood.BAD;
        this.drink = AlcoholLevel.SOBER;
    }
}
