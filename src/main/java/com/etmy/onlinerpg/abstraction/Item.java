package com.etmy.onlinerpg.abstraction;

import com.etmy.onlinerpg.item.ItemType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Item {

    protected String name;
    protected ItemType type;
    protected String description;
    protected String srcImage;
    protected int questId;

    public Item() {
    }
}
