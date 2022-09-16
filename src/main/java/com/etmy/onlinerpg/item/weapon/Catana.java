package com.etmy.onlinerpg.item.weapon;

import com.etmy.onlinerpg.abstraction.QuestItem;

public class Catana extends Weapon {

    {
        this.name = "Catana";
        this.questId = 1;
        this.damage = 20;
        this.description = "Unidentified katana: " + damage + " dmg";
        this.srcImage = "image/weapon/catana.png";
    }
}
