package com.etmy.onlinerpg.dto;

import com.etmy.onlinerpg.abstraction.Creature;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class LocationInfo {
    String name;
    String text;
    String srcImage;
    Set<String> locations;
    Set<Creature> creatures;

    public LocationInfo(String name, String text, String srcImage, Set<String> locations, Set<Creature> creatures) {
        this.name = name;
        this.text = text;
        this.srcImage = srcImage;
        this.locations = locations;
        this.creatures = creatures;
    }
}
