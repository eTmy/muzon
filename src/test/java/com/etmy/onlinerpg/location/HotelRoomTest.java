package com.etmy.onlinerpg.location;

import com.etmy.onlinerpg.abstraction.Location;
import com.etmy.onlinerpg.abstraction.Npc;
import com.etmy.onlinerpg.core.Account;
import com.etmy.onlinerpg.core.User;
import com.etmy.onlinerpg.npc.Catman;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelRoomTest {
    User user = new User(new Account());
    Location hotelRoom = new HotelRoom();

    @Test
    void should_add_item_when_user_have_not_finished_quest_find_first_weapon() {
        Npc npc = new Catman();
        user.addQuest(npc.getQuest(1));

        hotelRoom.buildItems(user);

        assertEquals(1, hotelRoom.getItems().size());

    }
}