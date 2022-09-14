package com.etmy.onlinerpg.npc;

import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.abstraction.Npc;
import com.etmy.onlinerpg.abstraction.Speakable;
import com.etmy.onlinerpg.core.Message;

public class EvilCatman extends Npc implements Speakable {
    {
        this.name = "Archie";
        this.nickname = "Half liter";
        this.description = "Jake's evil twin brother. Leading a dissolute life";
        this.srcImage = "image/creature/evil-catman.png";

        this.agility = 5;
        this.stamina = 20;
        this.strength = 10;
        this.intellect = 5;
        this.hp = stamina * 10;
    }

    @Override
    public Message speak(int id) {
        return this.getMessage(id);
    }
}
