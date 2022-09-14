package com.etmy.onlinerpg.abstraction;

import com.etmy.onlinerpg.core.Message;
import com.etmy.onlinerpg.exception.NotFoundMessageException;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.Set;

@Setter
@Getter
public abstract class Npc extends Creature implements Speakable {
    private Set<Message> dialog;

    public Message getMessage(int id){
        Optional<Message> message = dialog.stream().filter(m -> m.getId() == id).findAny();

        if (message.isEmpty()) {
            throw new NotFoundMessageException(this.name + " not found message with id " + id);
        }

        return message.get();
    }

    @Override
    public Message speak(int id) {
        return this.getMessage(id);
    }
}
