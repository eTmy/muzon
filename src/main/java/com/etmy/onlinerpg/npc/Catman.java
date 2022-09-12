package com.etmy.onlinerpg.npc;

import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.abstraction.Speakable;

public class Catman extends Creature implements Speakable {
    {
        this.name = "Jake";
        this.nickname = "Nervine";
        this.description = "A scientist cat who wants to achieve recognition of the intellectual abilities of his race in the modern world";
        this.srcImage = "image/creature/catman.png";

        this.agility = 18;
        this.stamina = 10;
        this.strength = 5;
        this.intellect = 17;
        this.hp = stamina * 10;
    }

    @Override
    public String speak() {
        return "Meow?";
    }
}
