package com.etmy.onlinerpg.item.weapon;

import com.etmy.onlinerpg.abstraction.QuestItem;

public class Catana extends Weapon {

    {
        this.name = "Catana";
        this.damage = 20;
        this.description = "Unidentified katana: " + damage + " dmg, CON+1";
        this.srcImage = "image/weapon/catana.png";
    }
}
