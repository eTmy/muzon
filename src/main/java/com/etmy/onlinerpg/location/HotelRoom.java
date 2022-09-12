package com.etmy.onlinerpg.location;

import com.etmy.onlinerpg.abstraction.Location;

public class HotelRoom extends Location {
    private static final String NAME = "Hotel-room";
    private static final String TEXT = "You are in the hotel-room";
    private static final String SRC_IMAGE = "image/hotel-room.png";

    {
        this.setName(NAME);
        this.text = TEXT;
        this.srcImage = SRC_IMAGE;

        locations.add("Hotel");
    }

}
