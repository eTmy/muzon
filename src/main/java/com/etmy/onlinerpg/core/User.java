package com.etmy.onlinerpg.core;

import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.abstraction.Item;
import com.etmy.onlinerpg.abstraction.HaveInventory;
import com.etmy.onlinerpg.exception.NotFoundMessageException;
import com.etmy.onlinerpg.item.ItemType;
import com.etmy.onlinerpg.item.weapon.Weapon;
import com.etmy.onlinerpg.abstraction.Location;
import com.etmy.onlinerpg.location.HotelRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class User extends Creature implements HaveInventory {
    private Location currentLocation;
    private Account account;
    private Set<Quest> quests = new HashSet<>();
    private List<Item> inventory = new ArrayList<>();
    private Weapon weapon;

    {
        //TODO подгружать из настроек сохраненную комнату
        this.currentLocation = new HotelRoom();
    }

    public User(Account account) {
        this.account = account;
    }

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public Quest getQuest(int id) {
        Optional<Quest> quest = quests.stream().filter(q -> q.getId() == id).findAny();

        if (quest.isEmpty()) {
            throw new NotFoundMessageException(this.name + " not found quest with id " + id);
        }

        return quest.get();
    }

    public boolean containQuest(int id) {
        return quests.stream().anyMatch(m -> m.getId() == id);
    }

    private void calculateStats(){
        this.damage += weapon.getDamage();
    }

    @Override
    public void takeItem(Item item) {
        if(item.getQuestId() > 0) {
            Quest quest = getQuest(item.getQuestId());
            quest.setFinished(true);
            addQuest(quest);
        }

        if (item.getType() == ItemType.WEAPON) {
            //TODO сделать экипировку предметов из экрана инвентаря
            this.weapon = (Weapon) item;
            inventory.add(item);
            calculateStats();
        }
    }

    @Override
    public void useItem(Item item) {

    }


}
