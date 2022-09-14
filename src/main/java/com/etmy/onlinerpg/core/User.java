package com.etmy.onlinerpg.core;

import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.exception.NotFoundMessageException;
import com.etmy.onlinerpg.location.Hotel;
import com.etmy.onlinerpg.abstraction.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Setter
@Getter
public class User extends Creature {
    private Location currentLocation;
    private Account account;
    private Set<Quest> quests = new HashSet<>();

    {
        //TODO подгружать из настроек сохраненную комнату
        this.currentLocation = new Hotel();
    }

    public User(Account account) {
        this.account = account;
    }

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public boolean containQuest(int id) {
        return quests.stream().anyMatch(m -> m.getId() == id);
    }
}
