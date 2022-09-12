package com.etmy.onlinerpg.location;

public class Collector extends Location {
    private static final String NAME = "Collector";
    private static final String TEXT = "You are in the collector";
    private static final String SRC_IMAGE = "image/collector.png";

    {
        this.setName(NAME);
        this.text = TEXT;
        this.srcImage = SRC_IMAGE;

        locations.add("City");

    }
}
