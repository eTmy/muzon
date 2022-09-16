package com.etmy.onlinerpg.abstraction;

import com.etmy.onlinerpg.core.User;
import com.etmy.onlinerpg.exception.CreatureNotFoundException;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public abstract class Location {
    protected String name;
    protected String text;
    protected String srcImage;
    protected Set<String> locations = new HashSet<>();
    protected Set<Creature> creatures = new HashSet<>();
    protected List<Item> items = new ArrayList<>();

    public Creature getCreature(String creatureName) {
        Optional<Creature> creature = creatures.stream()
                .filter(c -> c.getName().equals(creatureName))
                .findAny();

        if (creature.isEmpty()) {
           throw new CreatureNotFoundException("Location: "+name+". Not found creature: " + creatureName);
        }

        return creature.get();
    }

    public void buildItems(User user){}

}
