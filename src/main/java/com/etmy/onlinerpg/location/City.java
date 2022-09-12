package com.etmy.onlinerpg.location;

public class City extends Location {
    private static final String NAME = "City";
    private static final String TEXT = "You are in the city";
    private static final String SRC_IMAGE = "image/city.png";

    {
        this.setName(NAME);
        this.text = TEXT;
        this.srcImage = SRC_IMAGE;

        locations.add("Hotel");
        locations.add("Garden");
        locations.add("Slum");
        locations.add("Collector");

    }

}
