package com.etmy.onlinerpg.abstraction;

import com.etmy.onlinerpg.location.Hotel;
import lombok.Getter;

@Getter
public abstract class Creature {
    protected int hp;
    protected int strength;
    protected int agility;
    protected int stamina;
    protected int intellect;
    protected int damage;
    protected int armor;
    protected String name;
    protected String description;
    protected String nickname;
    protected String srcImage;

    {
        this.strength = 10;
        this.agility = 10;
        this.stamina = 10;
        this.intellect = 10;
        this.hp = 100;
        this.damage = 10;
        this.armor = 0;
    }


}
