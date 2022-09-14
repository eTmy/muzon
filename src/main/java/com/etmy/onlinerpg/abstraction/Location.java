package com.etmy.onlinerpg.abstraction;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public abstract class Location {
    protected String name;
    protected String text;
    protected String srcImage;
    protected Set<String> locations = new HashSet<>();
    protected Set<Creature> creatures = new HashSet<>();

}
