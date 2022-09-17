package com.etmy.onlinerpg.location;

import com.etmy.onlinerpg.abstraction.Location;
import com.etmy.onlinerpg.core.User;
import com.etmy.onlinerpg.item.weapon.Catana;

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

    @Override
    public void buildItems(User user) {

        if (user.getQuests().stream().anyMatch(quest -> "Find first weapon".equals(quest.getName())
                && !quest.isFinished())
                && user.getInventory().stream().noneMatch(item -> "Catana".equals(item.getName()))) {
            this.items.add(new Catana());
        }

    }
}
