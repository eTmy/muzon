package com.etmy.onlinerpg.npc;

import com.etmy.onlinerpg.abstraction.Attackable;
import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.abstraction.Speakable;

public class Rabbitman extends Creature implements Speakable, Attackable {
    {
        this.name = "Big Taddy";
        this.nickname = "Crooked ear";
        this.description = "Crazy rabbitman. Prey on humans to use their organs as his implants";
        this.srcImage = "image/creature/rabbitman.png";

        this.agility = 5;
        this.stamina = 5;
        this.strength = 30;
        this.intellect = 0;
        this.hp = stamina * 10;
    }

    @Override
    public String speak() {
        return "carrot?";
    }

    @Override
    public void attack() {

    }
}
