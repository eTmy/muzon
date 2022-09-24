package com.etmy.onlinerpg.location;

import com.etmy.onlinerpg.abstraction.Location;
import com.etmy.onlinerpg.npc.Catman;

public class Hotel extends Location {
    private static final String NAME = "Hotel";
    private static final String TEXT = "You are in the hotel";
    private static final String SRC_IMAGE = "image/hotel.png";

    public Hotel() {
        this.setName(NAME);
        this.text = TEXT;
        this.srcImage = SRC_IMAGE;

        locations.add("Shop");
        locations.add("City");
        locations.add("Hotel-room");

        creatures.add(new Catman());
    }

}
