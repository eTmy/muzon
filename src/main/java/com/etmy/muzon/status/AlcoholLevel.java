package com.etmy.muzon.status;

public enum AlcoholLevel {
    SOBER ("трезвый"),
    DRUNK ("пьяный");

    private String title;

    AlcoholLevel(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
