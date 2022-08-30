package com.etmy.muzon.status;

public enum MusicianLevel {
    NO("Отсутствует"),
    BAD("Плохой"),
    MEDIUM("Средний"),
    GOOD("Хороший"),
    HIGH("Высокий");

    String title;

    MusicianLevel(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
