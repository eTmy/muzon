package com.etmy.onlinerpg.item.weapon;

import com.etmy.onlinerpg.abstraction.Item;
import com.etmy.onlinerpg.item.ItemType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Weapon extends Item {
    int damage;

    {
        this.type = ItemType.WEAPON;
    }

    public Weapon() {

    }
}
