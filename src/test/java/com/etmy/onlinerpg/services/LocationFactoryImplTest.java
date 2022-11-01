package com.etmy.onlinerpg.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationFactoryImplTest {
    private LocationFactory locationFactory;

    @BeforeEach
    void setup() {
        locationFactory = new LocationFactoryImpl();
    }

    @Test
    void testCreateLocation_WhenArgHotelRoom_ShouldReturnNewHotelRoom() {
        assertEquals("Hotel-room", locationFactory.createLocation("Hotel-room").getName());
    }

    @Test
    void testCreateLocation_WhenArgHotel_ShouldReturnNewHotel() {
        assertEquals("Hotel", locationFactory.createLocation("Hotel").getName());
    }

    @Test
    void testCreateLocation_WhenArgCity_ShouldReturnNewCity() {
        assertEquals("City", locationFactory.createLocation("City").getName());
    }

    @Test
    void testCreateLocation_WhenArgGarden_ShouldReturnNewGarden() {
        assertEquals("Garden", locationFactory.createLocation("Garden").getName());
    }

    @Test
    void testCreateLocation_WhenArgCollector_ShouldReturnNewCollector() {
        assertEquals("Collector", locationFactory.createLocation("Collector").getName());
    }

    @Test
    void testCreateLocation_WhenArgSlum_ShouldReturnNewSlum() {
        assertEquals("Slum", locationFactory.createLocation("Slum").getName());
    }

    @Test
    void testCreateLocation_WhenArgShop_ShouldReturnNewShop() {
        assertEquals("Shop", locationFactory.createLocation("Shop").getName());
    }

    @Test
    void testCreateLocation_WhenArgNotFound_ShouldReturnNull() {
        assertNull(locationFactory.createLocation("asdjasfghakjhfajk"));
    }


}