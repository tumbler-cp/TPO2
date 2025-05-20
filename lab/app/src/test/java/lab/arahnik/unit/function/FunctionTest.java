package lab.arahnik.unit.function;

import lab.arahnik.math.function.Function;
import lab.arahnik.math.log.Ln;
import lab.arahnik.math.trig.Cos;
import lab.arahnik.math.trig.Sin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

class FunctionTest {

    private final Function function = new Function(
            new Sin(),
            new Cos(new Sin()),
            new Ln()
    );

    private final double acc = 1e-5;

    @Test
    void testValidNegativeInputs() {
        
        double[] validInputs = {-1, -Math.PI / 4, -Math.PI / 3, -2};

        for (double x : validInputs) {
            assertDoesNotThrow(() -> {
                double result = function.calculate(
                        BigDecimal.valueOf(x),
                        BigDecimal.valueOf(acc)
                ).doubleValue();
                assertFalse(Double.isNaN(result));
                assertFalse(Double.isInfinite(result));
            });
        }
    }

    @Test
    void testInvalidNegativeInputs() {
        
        double[] sinZeroPoints = {0, -Math.PI, -2 * Math.PI};
        double[] cosZeroPoints = {-Math.PI / 2, -3 * Math.PI / 2};

        for (double x : sinZeroPoints) {
            assertThrows(ArithmeticException.class, () -> function.calculate(
                    BigDecimal.valueOf(x),
                    BigDecimal.valueOf(acc)
            ));
        }

        for (double x : cosZeroPoints) {
            assertThrows(ArithmeticException.class, () -> function.calculate(
                    BigDecimal.valueOf(x),
                    BigDecimal.valueOf(acc)
            ));
        }
    }
    
    @Test
    void testValidPositiveInputs() {
        
        double[] validInputs = {Math.PI / 4, Math.PI / 3, 2, 10};

        for (double x : validInputs) {
            assertDoesNotThrow(() -> {
                double result = function.calculate(
                        BigDecimal.valueOf(x),
                        BigDecimal.valueOf(acc)
                ).doubleValue();
                assertFalse(Double.isNaN(result));
                assertFalse(Double.isInfinite(result));
            });
        }
    }

    @Test
    void testInvalidPositiveInputs() {
        double[] invalidInputs = {1};

        for (double x : invalidInputs) {
            assertThrows(ArithmeticException.class, () -> function.calculate(
                    BigDecimal.valueOf(x),
                    BigDecimal.valueOf(acc)
            ));
        }
    }
}
