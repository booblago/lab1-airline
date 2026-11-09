package ua.kpi.ips32.airline.model;

public class CargoAircraft extends Aircraft {

    private final double cargoCapacityTons;

    public CargoAircraft(String model, int flightRangeKm, double fuelConsumptionPerHour,
                         double cargoCapacityTons) {
        super(model, flightRangeKm, fuelConsumptionPerHour);
        if (cargoCapacityTons <= 0) {
            throw new IllegalArgumentException("Cargo capacity must be positive");
        }
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public int getPassengerCapacity() {
        // вантажний літак не перевозить пасажирів
        return 0;
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
