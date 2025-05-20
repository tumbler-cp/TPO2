package lab.arahnik;

import lab.arahnik.math.function.Function;
import lab.arahnik.util.CSVGenerator;

public class App {

    public static void main(String[] args) {
        Function function = new Function();
        CSVGenerator.generate("output.csv", function, 1e-1, 1e-5, 1.1, 10);
    }

}

