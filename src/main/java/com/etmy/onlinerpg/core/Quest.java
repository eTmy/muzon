package com.etmy.onlinerpg.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Quest {
    int id;
    String name;
    String description;
    boolean accepted;
    boolean finished;
}
