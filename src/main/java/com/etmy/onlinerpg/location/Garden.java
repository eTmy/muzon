package com.etmy.onlinerpg.location;

public class Garden extends Location {
    private static final String NAME = "Garden";
    private static final String TEXT = "You are in the garden";
    private static final String SRC_IMAGE = "image/garden.png";

    {
        this.setName(NAME);
        this.text = TEXT;
        this.srcImage = SRC_IMAGE;

        locations.add("City");
    }

}
