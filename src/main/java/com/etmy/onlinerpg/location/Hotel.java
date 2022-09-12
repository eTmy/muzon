package com.etmy.onlinerpg.location;

public class Hotel extends Location {
    private static final String NAME = "Hotel";
    private static final String TEXT = "You are in the hotel";
    private static final String SRC_IMAGE = "image/player_house.png";

    {
        this.setName(NAME);
        this.text = TEXT;
        this.srcImage = SRC_IMAGE;

        locations.add("City");
    }

}
