package ua.kpi.ips32.airline.model;

public class PassengerAircraft extends Aircraft {

    private final int seats;
    private final double cargoCapacityTons;

    public PassengerAircraft(String model, int flightRangeKm, double fuelConsumptionPerHour,
                             int seats, double cargoCapacityTons) {
        super(model, flightRangeKm, fuelConsumptionPerHour);
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats must be positive");
        }
        if (cargoCapacityTons < 0) {
            throw new IllegalArgumentException("Cargo capacity cannot be negative");
        }
        this.seats = seats;
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public int getPassengerCapacity() {
        return seats;
    }

    @Override
    public double getCargoCapacityTons() {
        return cargoCapacityTons;
    }

    @Override
    public String getAircraftType() {
        return "Passenger";
    }
}
