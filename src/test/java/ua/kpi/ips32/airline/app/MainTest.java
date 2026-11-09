package ua.kpi.ips32.airline.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void mainPrintsCompanyTotalsAndSearch() throws Exception {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            Main.main(new String[]{});
        } finally {
            System.setOut(originalOut);
        }

        String output = buffer.toString();
        assertTrue(output.contains("Sky Ukraine Airlines"));
        assertTrue(output.contains("Total passenger capacity"));
        assertTrue(output.contains("Total cargo capacity"));
        assertTrue(output.contains("Fleet sorted by flight range"));
        assertTrue(output.contains("fuel consumption"));
    }
}
