package com.etmy.onlinerpg.npc;

import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.abstraction.Speakable;

public class Fanatic extends Creature implements Speakable {

    {
        this.name = "Fanatic";
        this.nickname = "Red hood";
        this.description = "A fanatic serves the people in an attempt to exterminate the last representatives of the human race.";
        this.srcImage = "image/creature/fanatic.png";

        this.agility = 5;
        this.stamina = 10;
        this.strength = 5;
        this.intellect = 20;
        this.hp = stamina * 10;
    }

    @Override
    public String speak() {
        return "Great, we're alone here! NOW YOU WILL DIE!!!";
    }
}
