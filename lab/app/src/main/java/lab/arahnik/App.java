package lab.arahnik;

import lab.arahnik.math.log.Ln;
import lab.arahnik.math.log.ext.Log;
import lab.arahnik.util.CSVGenerator;

public class App {

    public static void main(String[] args) {
        CSVGenerator.generate("output.csv", new Log(10), 1e-2, 1e-5, -10, 10, -20, 20);
    }

}

