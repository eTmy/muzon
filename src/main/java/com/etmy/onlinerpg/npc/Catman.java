package com.etmy.onlinerpg.npc;

import com.etmy.onlinerpg.abstraction.Npc;
import com.etmy.onlinerpg.core.Message;

import java.util.HashSet;
import java.util.Set;

public class Catman extends Npc  {
    {
        this.name = "Jake";
        this.nickname = "Nervine";
        this.description = "A scientist cat who wants to achieve recognition of the intellectual abilities of his race in the modern world";
        this.srcImage = "image/creature/catman.png";

        this.agility = 18;
        this.stamina = 10;
        this.strength = 5;
        this.intellect = 17;
        this.hp = stamina * 10;

        this.setDialog(buildDialog());

    }

    public Set<Message> buildDialog() {
        Set<Message> messages = new HashSet<>();

        messages.add(
                Message.builder()
                        .id(1)
                        .text("The cat looks at you with interest")
                        .answers(Set.of(
                                Message.Answer.builder()
                                        .text("Meow?")
                                        .nextMessageId(2)
                                        .build(),
                                Message.Answer.builder()
                                        .text("Leave")
                                        .build()
                        ))
                        .build()
        );

        messages.add(
                Message.builder()
                        .id(2)
                        .text("...")
                        .answers(Set.of(
                                Message.Answer.builder()
                                        .text("MEOW... MEOW... MEOW")
                                        .nextMessageId(3)
                                        .build()
                        ))
                        .build()
        );

        messages.add(
                Message.builder()
                        .id(3)
                        .text("BUUUUUUUUU!!!")
                        .answers(Set.of(
                                Message.Answer.builder()
                                        .text("OMG!!! Damn, you scared me! You can speak?")
                                        .nextMessageId(4)
                                        .build(),
                                Message.Answer.builder()
                                        .text("Nice try, I'm not scared")
                                        .nextMessageId(4)
                                        .build(),
                                Message.Answer.builder()
                                        .text("Who are you?")
                                        .nextMessageId(4)
                                        .build()
                        ))
                        .build()
        );

        messages.add(
                Message.builder()
                        .id(4)
                        .text("I clearly have more intelligence than you." +
                                " Never met a race of talking cats?" +
                                " Are you straight out of the 2000s?")
                        .answers(Set.of(
                                Message.Answer.builder()
                                        .text("Never. I woke up in a hotel and I don't remember anything")
                                        .nextMessageId(5)
                                        .build(),
                                Message.Answer.builder()
                                        .text("Leave")
                                        .build()
                        ))
                        .build()
        );

        return messages;
    }
}
