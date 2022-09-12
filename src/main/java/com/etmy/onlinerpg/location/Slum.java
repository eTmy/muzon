package com.etmy.onlinerpg.location;

public class Slum extends Location {
    private static final String NAME = "Slum";
    private static final String TEXT = "You are in the slum";
    private static final String SRC_IMAGE = "image/slum.png";

    {
        this.setName(NAME);
        this.text = TEXT;
        this.srcImage = SRC_IMAGE;

        locations.add("City");
    }


}
