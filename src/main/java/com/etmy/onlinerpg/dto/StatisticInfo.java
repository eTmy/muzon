package com.etmy.onlinerpg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class StatisticInfo {
    private String login;
    private int hp;
    private int strength;
    private int agility;
    private int stamina;
    private int intellect;
    private int damage;
    private int armor;
}
