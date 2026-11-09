package ua.kpi.ips32.airline.company;

import ua.kpi.ips32.airline.model.Aircraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AirlineCompany {

    private final String name;
    private final List<Aircraft> fleet;

    public AirlineCompany(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be empty");
        }
        this.name = name.trim();
        this.fleet = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAircraft(Aircraft aircraft) {
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft cannot be null");
        }
        fleet.add(aircraft);
    }

    public List<Aircraft> getFleet() {
        return Collections.unmodifiableList(fleet);
    }

    public int getTotalPassengerCapacity() {
        int total = 0;
        for (Aircraft aircraft : fleet) {
            total += aircraft.getPassengerCapacity();
        }
        return total;
    }

    public double getTotalCargoCapacityTons() {
        double total = 0;
        for (Aircraft aircraft : fleet) {
            total += aircraft.getCargoCapacityTons();
        }
        return total;
    }

    public void sortByFlightRange() {
        fleet.sort(Comparator.comparingInt(Aircraft::getFlightRangeKm));
    }

    public List<Aircraft> findByFuelConsumption(double minFuel, double maxFuel) {
        if (minFuel < 0 || maxFuel < minFuel) {
            throw new IllegalArgumentException("Invalid fuel consumption range");
        }
        List<Aircraft> result = new ArrayList<>();
        for (Aircraft aircraft : fleet) {
            double fuel = aircraft.getFuelConsumptionPerHour();
            if (fuel >= minFuel && fuel <= maxFuel) {
                result.add(aircraft);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "AirlineCompany{name='" + name + "', fleetSize=" + fleet.size() + "}";
    }
}
