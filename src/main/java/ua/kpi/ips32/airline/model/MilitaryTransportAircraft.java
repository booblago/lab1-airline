package ua.kpi.ips32.airline.model;

/**
 * Військово-транспортний літак: може перевозити і людей, і вантаж.
 */
public class MilitaryTransportAircraft extends Aircraft {

    private final int troopCapacity;
    private final double cargoCapacityTons;

    public MilitaryTransportAircraft(String model, int flightRangeKm, double fuelConsumptionPerHour,
                                     int troopCapacity, double cargoCapacityTons) {
        super(model, flightRangeKm, fuelConsumptionPerHour);
        if (troopCapacity <= 0) {
            throw new IllegalArgumentException("Troop capacity must be positive");
        }
        if (cargoCapacityTons <= 0) {
            throw new IllegalArgumentException("Cargo capacity must be positive");
        }
        this.troopCapacity = troopCapacity;
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public int getPassengerCapacity() {
        return troopCapacity;
    }

    @Override
    public double getCargoCapacityTons() {
        return cargoCapacityTons;
    }

    @Override
    public String getAircraftType() {
        return "MilitaryTransport";
    }
}
