package com.etmy.onlinerpg.location;

public class ActionLocationFactory implements LocationFactory {
    @Override
    public Location createLocation(String name) {
        switch (name) {
            case "Стартовая комната" : return new StartRoom();
            case "Сад"    : return new Garden();
            default          : return null;
        }
    }
}
