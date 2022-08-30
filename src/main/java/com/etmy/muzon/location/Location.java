package com.etmy.muzon.location;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public abstract class Location {

    protected String name;
    protected String questText;
    protected Set<String> actions = new HashSet<>();

    protected abstract void doAction(String action);

}
