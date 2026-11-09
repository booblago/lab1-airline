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
    void cargoAircraftReturnsCrewAsPassengers() {
        CargoAircraft plane = new CargoAircraft("An-124", 5400, 12.0, 120.0, 6);

        assertEquals(6, plane.getPassengerCapacity());
        assertEquals(120.0, plane.getCargoCapacityTons());
        assertEquals("Cargo", plane.getAircraftType());
    }

    @Test
    void militaryTransportAircraftWorks() {
        MilitaryTransportAircraft plane =
                new MilitaryTransportAircraft("Il-76", 7000, 8.0, 120, 40.0);

        assertEquals(120, plane.getPassengerCapacity());
        assertEquals(40.0, plane.getCargoCapacityTons());
        assertEquals("MilitaryTransport", plane.getAircraftType());
    }

    @Test
    void invalidModelThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new PassengerAircraft(" ", 1000, 1.0, 10, 1.0));
    }

    @Test
    void invalidRangeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new CargoAircraft("Test", 0, 1.0, 10.0, 2));
    }

    @Test
    void toStringContainsModel() {
        Aircraft plane = new PassengerAircraft("A320", 6000, 2.0, 150, 3.0);
        assertTrue(plane.toString().contains("A320"));
        assertTrue(plane.toString().contains("Passenger"));
    }
}
