package com.etmy.onlinerpg.location;

public class LocationFactoryImpl implements LocationFactory {
    @Override
    public Location createLocation(String name) {
        switch (name) {
            case "Hotel":
                return new Hotel();
            case "City":
                return new City();
            case "Garden":
                return new Garden();
            case "Collector":
                return new Collector();
            case "Slum":
                return new Slum();
            default:
                return null;
        }
    }
}
