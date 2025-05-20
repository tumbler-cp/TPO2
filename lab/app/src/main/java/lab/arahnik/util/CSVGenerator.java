package lab.arahnik.util;

import lab.arahnik.math.MathModule;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class CSVGenerator {
    public static void generate(String filename, MathModule module, double step, double acc, double start, double end) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X,Y\n");
            for (double x = start; x <= end; x += step) {
                BigDecimal y = module.calculate(BigDecimal.valueOf(x), BigDecimal.valueOf(acc));
                writer.write(x + "," + y + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
