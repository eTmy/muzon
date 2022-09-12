package com.etmy.onlinerpg.npc;

import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.abstraction.Speakable;

public class EvilCatman extends Creature implements Speakable {
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
    public String speak() {
        return "Meow?";
    }
}
