package ua.kpi.ips32.airline.model;

public class CargoAircraft extends Aircraft {

    private final double cargoCapacityTons;
    private final int crewSeats;

    public CargoAircraft(String model, int flightRangeKm, double fuelConsumptionPerHour,
                         double cargoCapacityTons, int crewSeats) {
        super(model, flightRangeKm, fuelConsumptionPerHour);
        if (cargoCapacityTons <= 0) {
            throw new IllegalArgumentException("Cargo capacity must be positive");
        }
        if (crewSeats < 0) {
            throw new IllegalArgumentException("Crew seats cannot be negative");
        }
        this.cargoCapacityTons = cargoCapacityTons;
        this.crewSeats = crewSeats;
    }

    @Override
    public int getPassengerCapacity() {
        // у вантажного літака "пасажири" = екіпаж
        return crewSeats;
    }

    @Override
    public double getCargoCapacityTons() {
        return cargoCapacityTons;
    }

    @Override
    public String getAircraftType() {
        return "Cargo";
    }
}
