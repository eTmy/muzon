package com.etmy.onlinerpg.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Message {
    private int id;
    private String text;
    private Set<Answer> answers;

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Answer {
        private String text;
        private int nextMessageId;
        private int questId;
    }
}
