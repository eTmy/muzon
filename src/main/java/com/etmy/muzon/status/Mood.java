package com.etmy.muzon.status;

public enum Mood {
    GOOD("Хорошее"),
    BAD("Плохое");

    private final String title;

    Mood(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}


