package ua.kpi.ips32.airline.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ua.kpi.ips32.airline.model.Aircraft;
import ua.kpi.ips32.airline.model.CargoAircraft;
import ua.kpi.ips32.airline.model.PassengerAircraft;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AircraftFileLoaderTest {

    private final AircraftFileLoader loader = new AircraftFileLoader();

    @TempDir
    Path tempDir;

    @Test
    void loadsFromClasspathResource() throws IOException {
        List<Aircraft> list = loader.loadFromClasspath("aircrafts.txt");

        assertTrue(list.size() >= 5);
        assertTrue(list.stream().anyMatch(a -> a.getModel().contains("Boeing")));
    }

    @Test
    void loadsFromFile() throws IOException {
        Path file = tempDir.resolve("fleet.txt");
        String content = ""
                + "# comment\n"
                + "PASSENGER;TestJet;4000;2.0;120;3.0\n"
                + "CARGO;BigCargo;5000;9.5;80.0;4\n";
        Files.writeString(file, content, StandardCharsets.UTF_8);

        List<Aircraft> list = loader.loadFromFile(file);

        assertEquals(2, list.size());
        assertInstanceOf(PassengerAircraft.class, list.get(0));
        assertInstanceOf(CargoAircraft.class, list.get(1));
        assertEquals(120, list.get(0).getPassengerCapacity());
        assertEquals(80.0, list.get(1).getCargoCapacityTons());
    }

    @Test
    void badLineThrowsIoException() throws IOException {
        Path file = tempDir.resolve("bad.txt");
        Files.writeString(file, "PASSENGER;OnlyThree;1\n", StandardCharsets.UTF_8);

        assertThrows(IOException.class, () -> loader.loadFromFile(file));
    }

    @Test
    void missingClasspathResourceThrows() {
        assertThrows(IOException.class, () -> loader.loadFromClasspath("no-such-file.txt"));
    }
}
