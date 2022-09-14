package com.etmy.onlinerpg.npc;

import com.etmy.onlinerpg.abstraction.Npc;
import com.etmy.onlinerpg.core.Message;
import com.etmy.onlinerpg.core.Quest;

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
        this.setAvailableQuests(buildQuests());

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
                                        .nextMessageId(0)
                                        .text("Leave")
                                        .build()
                        ))
                        .build()
        );

        messages.add(
                Message.builder()
                        .id(2)
                        .text("Continues to look at you with interest")
                        .answers(Set.of(
                                Message.Answer.builder()
                                        .text("MEOW... MEOW... MEOW...")
                                        .nextMessageId(3)
                                        .build()
                        ))
                        .build()
        );

        messages.add(
                Message.builder()
                        .id(3)
                        .text("You're not very smart, yep?")
                        .answers(Set.of(
                                Message.Answer.builder()
                                        .text("You can speak? Damn, you scared me!")
                                        .nextMessageId(4)
                                        .build(),
                                Message.Answer.builder()
                                        .text("Yep, I'd better leave")
                                        .nextMessageId(0)
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
                                        .nextMessageId(0)
                                        .text("Leave")
                                        .build()
                        ))
                        .build()
        );

        messages.add(
                Message.builder()
                        .id(5)
                        .text("It is now 2343. A lot has changed in 300 years. All races have evolved through the use " +
                                "of prosthetics from human organs. Because of this, people were almost exterminated, " +
                                "and the remains are being hunted. Lucky you met me first. You need to find a weapon, " +
                                "outside the hotel many will try to kill you for your organs. Look at the weapon " +
                                "in Hotel-room")
                        .answers(Set.of(
                                Message.Answer.builder()
                                        .text("Okay, I'll go get my weapon.")
                                        .questId(1)
                                        .build(),
                                Message.Answer.builder()
                                        .nextMessageId(0)
                                        .text("Leave")
                                        .build()
                        ))
                        .build()
        );

        return messages;
    }

    public Set<Quest> buildQuests() {
        Set<Quest> quests = new HashSet<>();

        quests.add(Quest.builder()
                .id(1)
                .name("Find first weapon")
                .description("Find a weapon in your Hotel-room")
                .build()
        );

        return quests;
    }
}
