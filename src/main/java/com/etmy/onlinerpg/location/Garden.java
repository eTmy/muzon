package com.etmy.onlinerpg.location;

public class Garden extends Location {
    private static final String NAME = "Сад";
    private static final String TEXT = " Вы входите в сад";

    {
        this.setName(NAME);
        this.setQuestText(TEXT);
        this.actions.add("На главную площадь");
        this.actions.add("В стартовую комнату");
        this.actions.add("Плюнуть на все и уехать отсюда");
    }

    @Override
    public void doAction(String action) {
        switch (action) {
            case "В стартовую комнату" : move(action);
        }
    }

    @Override
    public Location move(String locationName) {
        switch (locationName) {
            case "В стартовую комнату" : return new StartRoom();
            default : return null;
        }
    }
}
