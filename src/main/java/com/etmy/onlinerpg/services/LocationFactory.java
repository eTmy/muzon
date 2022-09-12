package com.etmy.onlinerpg.services;

import com.etmy.onlinerpg.abstraction.Location;

public interface LocationFactory {
    Location createLocation(String name);
}
