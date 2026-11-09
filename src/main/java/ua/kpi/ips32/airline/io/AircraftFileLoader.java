package ua.kpi.ips32.airline.io;

import ua.kpi.ips32.airline.model.Aircraft;
import ua.kpi.ips32.airline.model.BusinessJet;
import ua.kpi.ips32.airline.model.CargoAircraft;
import ua.kpi.ips32.airline.model.PassengerAircraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Читає параметри літаків з текстового файлу.
 * Формат рядка:
 * TYPE;model;rangeKm;fuelPerHour;param1;param2
 *
 * PASSENGER    -> seats; cargoTons
 * CARGO        -> cargoTons; (param2 ignored, put 0)
 * BUSINESS_JET -> seats; cargoTons
 */
public class AircraftFileLoader {

    public List<Aircraft> loadFromClasspath(String resourceName) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName);
        if (stream == null) {
            throw new IOException("Resource not found: " + resourceName);
        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            return parse(reader);
        }
    }

    public List<Aircraft> loadFromFile(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return parse(reader);
        }
    }

    private List<Aircraft> parse(BufferedReader reader) throws IOException {
        List<Aircraft> aircraftList = new ArrayList<>();
        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            try {
                aircraftList.add(parseLine(line));
            } catch (RuntimeException ex) {
                throw new IOException("Error in line " + lineNumber + ": " + ex.getMessage(), ex);
            }
        }
        return aircraftList;
    }

    private Aircraft parseLine(String line) {
        String[] parts = line.split(";");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Expected 6 fields separated by ';'");
        }

        String type = parts[0].trim().toUpperCase();
        String model = parts[1].trim();
        int rangeKm = Integer.parseInt(parts[2].trim());
        double fuel = Double.parseDouble(parts[3].trim());
        double first = Double.parseDouble(parts[4].trim());
        double second = Double.parseDouble(parts[5].trim());

        switch (type) {
            case "PASSENGER":
                return new PassengerAircraft(model, rangeKm, fuel, (int) first, second);
            case "CARGO":
                return new CargoAircraft(model, rangeKm, fuel, first);
            case "BUSINESS_JET":
                return new BusinessJet(model, rangeKm, fuel, (int) first, second);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + type);
        }
    }
}
