package ua.kpi.ips32.airline.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AircraftTest {

    @Test
    void passengerAircraftStoresValues() {
        PassengerAircraft plane = new PassengerAircraft("Boeing 737", 5000, 2.5, 180, 4.0);

        assertEquals("Boeing 737", plane.getModel());
        assertEquals(5000, plane.getFlightRangeKm());
        assertEquals(2.5, plane.getFuelConsumptionPerHour());
        assertEquals(180, plane.getPassengerCapacity());
        assertEquals(4.0, plane.getCargoCapacityTons());
        assertEquals("Passenger", plane.getAircraftType());
    }

    @Test
    void cargoAircraftHasNoPassengers() {
        CargoAircraft plane = new CargoAircraft("An-124", 5400, 12.0, 120.0);

        assertEquals(0, plane.getPassengerCapacity());
        assertEquals(120.0, plane.getCargoCapacityTons());
        assertEquals("Cargo", plane.getAircraftType());
    }

    @Test
    void businessJetWorks() {
        BusinessJet plane = new BusinessJet("Gulfstream G650", 12000, 1.7, 18, 1.2);

        assertEquals(18, plane.getPassengerCapacity());
        assertEquals(1.2, plane.getCargoCapacityTons());
        assertEquals("BusinessJet", plane.getAircraftType());
    }

    @Test
    void invalidModelThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new PassengerAircraft(" ", 1000, 1.0, 10, 1.0));
    }

    @Test
    void invalidRangeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new CargoAircraft("Test", 0, 1.0, 10.0));
    }

    @Test
    void toStringContainsModel() {
        Aircraft plane = new PassengerAircraft("A320", 6000, 2.0, 150, 3.0);
        assertTrue(plane.toString().contains("A320"));
        assertTrue(plane.toString().contains("Passenger"));
    }
}
