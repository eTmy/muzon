package com.etmy.onlinerpg.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StatisticInfo {
    private String username;
    private int hp;
    private int strength;
    private int agility;
    private int stamina;
    private int intellect;

    public StatisticInfo(String username, int hp, int strength, int agility, int stamina, int intellect) {
        this.username = username;
        this.hp = hp;
        this.strength = strength;
        this.agility = agility;
        this.stamina = stamina;
        this.intellect = intellect;
    }
}
