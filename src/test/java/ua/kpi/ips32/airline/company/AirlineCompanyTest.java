package ua.kpi.ips32.airline.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.kpi.ips32.airline.model.Aircraft;
import ua.kpi.ips32.airline.model.BusinessJet;
import ua.kpi.ips32.airline.model.CargoAircraft;
import ua.kpi.ips32.airline.model.PassengerAircraft;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AirlineCompanyTest {

    private AirlineCompany company;

    @BeforeEach
    void setUp() {
        company = new AirlineCompany("Test Air");
        company.addAircraft(new PassengerAircraft("B737", 5600, 2.4, 189, 5.0));
        company.addAircraft(new CargoAircraft("An-124", 5400, 12.0, 120.0));
        company.addAircraft(new BusinessJet("G650", 12200, 1.7, 18, 1.2));
        company.addAircraft(new PassengerAircraft("CRJ900", 2900, 1.5, 90, 1.5));
    }

    @Test
    void calculatesTotalPassengerCapacity() {
        // 189 + 0 + 18 + 90
        assertEquals(297, company.getTotalPassengerCapacity());
    }

    @Test
    void calculatesTotalCargoCapacity() {
        // 5 + 120 + 1.2 + 1.5
        assertEquals(127.7, company.getTotalCargoCapacityTons(), 0.0001);
    }

    @Test
    void sortsByFlightRangeAscending() {
        company.sortByFlightRange();
        List<Aircraft> fleet = company.getFleet();

        assertEquals("CRJ900", fleet.get(0).getModel());
        assertEquals("An-124", fleet.get(1).getModel());
        assertEquals("B737", fleet.get(2).getModel());
        assertEquals("G650", fleet.get(3).getModel());
    }

    @Test
    void findsAircraftByFuelRange() {
        List<Aircraft> found = company.findByFuelConsumption(1.6, 3.0);

        assertEquals(2, found.size());
        assertTrue(found.stream().anyMatch(a -> a.getModel().equals("B737")));
        assertTrue(found.stream().anyMatch(a -> a.getModel().equals("G650")));
    }

    @Test
    void findByFuelReturnsEmptyWhenNothingMatches() {
        List<Aircraft> found = company.findByFuelConsumption(50.0, 60.0);
        assertTrue(found.isEmpty());
    }

    @Test
    void invalidFuelRangeThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> company.findByFuelConsumption(5.0, 1.0));
    }

    @Test
    void cannotAddNullAircraft() {
        assertThrows(IllegalArgumentException.class, () -> company.addAircraft(null));
    }

    @Test
    void emptyNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new AirlineCompany(""));
    }
}
