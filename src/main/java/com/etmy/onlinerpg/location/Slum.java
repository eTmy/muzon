package com.etmy.onlinerpg.location;

import com.etmy.onlinerpg.abstraction.Location;
import com.etmy.onlinerpg.npc.EvilCatman;
import com.etmy.onlinerpg.npc.Rabbitman;

public class Slum extends Location {
    private static final String NAME = "Slum";
    private static final String TEXT = "You are in the slum";
    private static final String SRC_IMAGE = "image/slum.png";

    {
        this.setName(NAME);
        this.text = TEXT;
        this.srcImage = SRC_IMAGE;

        locations.add("City");

        creatures.add(new EvilCatman());
        creatures.add(new Rabbitman());
    }


}
