package com.etmy.onlinerpg.npc;

import com.etmy.onlinerpg.abstraction.Attackable;
import com.etmy.onlinerpg.abstraction.Npc;
import com.etmy.onlinerpg.abstraction.Speakable;
import com.etmy.onlinerpg.core.Message;

public class Rabbitman extends Npc implements Speakable, Attackable {
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
    public Message speak(int id) {
        return this.getMessage(id);
    }

    @Override
    public void attack() {

    }
}
