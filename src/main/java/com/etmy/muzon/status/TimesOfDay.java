package com.etmy.muzon.status;

public enum TimesOfDay {
    MORNING("Утро"),
    DAY("День"),
    EVENING("Вечер"),
    NIGHT("Ночь");

    private final String title;

    TimesOfDay(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
