package com.etmy.onlinerpg.location;

import com.etmy.onlinerpg.abstraction.Location;

public class Shop extends Location {
    private static final String NAME = "Shop";
    private static final String TEXT = "You are in the shop";
    private static final String SRC_IMAGE = "image/shop.png";

    {
        this.setName(NAME);
        this.text = TEXT;
        this.srcImage = SRC_IMAGE;

        locations.add("Hotel");
    }
}
