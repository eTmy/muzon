package com.etmy.onlinerpg.dto;

import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.abstraction.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class LocationInfo {
    String name;
    String text;
    String srcImage;
    Set<String> locations;
    Set<Creature> creatures;
    List<Item> items;

}
