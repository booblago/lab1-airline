package ua.kpi.ips32.airline.model;

/**
 * Базовий клас для всіх літаків авіакомпанії.
 */
public abstract class Aircraft {

    private final String model;
    private final int flightRangeKm;
    private final double fuelConsumptionPerHour;

    protected Aircraft(String model, int flightRangeKm, double fuelConsumptionPerHour) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model name cannot be empty");
        }
        if (flightRangeKm <= 0) {
            throw new IllegalArgumentException("Flight range must be positive");
        }
        if (fuelConsumptionPerHour <= 0) {
            throw new IllegalArgumentException("Fuel consumption must be positive");
        }
        this.model = model.trim();
        this.flightRangeKm = flightRangeKm;
        this.fuelConsumptionPerHour = fuelConsumptionPerHour;
    }

    public String getModel() {
        return model;
    }

    public int getFlightRangeKm() {
        return flightRangeKm;
    }

    public double getFuelConsumptionPerHour() {
        return fuelConsumptionPerHour;
    }

    public abstract int getPassengerCapacity();

    public abstract double getCargoCapacityTons();

    public abstract String getAircraftType();

    @Override
    public String toString() {
        return getAircraftType() + " " + model
                + " [range=" + flightRangeKm + " km"
                + ", fuel=" + fuelConsumptionPerHour + " t/h"
                + ", passengers=" + getPassengerCapacity()
                + ", cargo=" + getCargoCapacityTons() + " t]";
    }
}
