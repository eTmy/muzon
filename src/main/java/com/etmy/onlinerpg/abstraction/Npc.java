package com.etmy.onlinerpg.abstraction;

import com.etmy.onlinerpg.core.Message;
import com.etmy.onlinerpg.core.Quest;
import com.etmy.onlinerpg.exception.NotFoundMessageException;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.Set;

@Setter
@Getter
public abstract class Npc extends Creature implements Speakable {
    private Set<Message> dialog;
    private Set<Quest> availableQuests;

    @Override
    public Message speak(int id) {
        return this.getMessage(id);
    }
    public Message getMessage(int id){
        Optional<Message> message = dialog.stream().filter(m -> m.getId() == id).findAny();

        if (message.isEmpty()) {
            throw new NotFoundMessageException(this.name + " not found message with id " + id);
        }

        return message.get();
    }

    public Quest getQuest(int id) {
        Optional<Quest> quest = availableQuests.stream().filter(m -> m.getId() == id).findAny();

        if (quest.isEmpty()) {
            throw new NotFoundMessageException(this.name + " not found message with id " + id);
        }

        return quest.get();
    }

}
