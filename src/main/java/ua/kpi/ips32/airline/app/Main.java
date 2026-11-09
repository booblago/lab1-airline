package ua.kpi.ips32.airline.app;

import ua.kpi.ips32.airline.company.AirlineCompany;
import ua.kpi.ips32.airline.io.AircraftFileLoader;
import ua.kpi.ips32.airline.model.Aircraft;

import java.util.List;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws Exception {
        AircraftFileLoader loader = new AircraftFileLoader();
        List<Aircraft> aircraftList = loader.loadFromClasspath("aircrafts.txt");

        AirlineCompany company = new AirlineCompany("Sky Ukraine Airlines");
        for (Aircraft aircraft : aircraftList) {
            company.addAircraft(aircraft);
        }

        System.out.println("Company: " + company.getName());
        System.out.println("Total passenger capacity: " + company.getTotalPassengerCapacity());
        System.out.printf("Total cargo capacity (t): %.1f%n", company.getTotalCargoCapacityTons());

        company.sortByFlightRange();
        System.out.println("\nFleet sorted by flight range:");
        for (Aircraft aircraft : company.getFleet()) {
            System.out.println(" - " + aircraft);
        }

        double minFuel = 2.0;
        double maxFuel = 8.0;
        List<Aircraft> found = company.findByFuelConsumption(minFuel, maxFuel);
        System.out.println("\nAircraft with fuel consumption in [" + minFuel + "; " + maxFuel + "]:");
        if (found.isEmpty()) {
            System.out.println(" - none");
        } else {
            for (Aircraft aircraft : found) {
                System.out.println(" - " + aircraft);
            }
        }
    }
}
