package com.etmy.onlinerpg.services;

import com.etmy.onlinerpg.abstraction.Location;
import com.etmy.onlinerpg.location.*;

public class LocationFactoryImpl implements LocationFactory {
    @Override
    public Location createLocation(String name) {
        switch (name) {
            case "Hotel-room":
                return new HotelRoom();
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
            case "Shop":
                return new Shop();
            default:
                return null;
        }
    }
}
