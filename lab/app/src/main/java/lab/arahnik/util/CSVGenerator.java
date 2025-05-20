package lab.arahnik.util;

import lab.arahnik.math.MathModule;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class CSVGenerator {
    public static void generate(String filename, MathModule module, double step, double acc, double start, double end, double yMin, double yMax) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X,Y\n");
            for (double x = start; x <= end; x += step) {
                BigDecimal y;
                try {
                    y = module.calculate(BigDecimal.valueOf(x), BigDecimal.valueOf(acc));
                } catch (Exception a) {
                    continue;
                }
                if (y.doubleValue() < yMin || y.doubleValue() > yMax) continue;
                writer.write(x + "," + y + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
