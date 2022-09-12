package com.etmy.onlinerpg.dto;

import com.etmy.onlinerpg.location.Location;
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

    public LocationInfo(String name, String text, String srcImage, Set<String> locations) {
        this.name = name;
        this.text = text;
        this.srcImage = srcImage;
        this.locations = locations;
    }
}
