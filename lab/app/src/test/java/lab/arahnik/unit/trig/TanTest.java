package lab.arahnik.unit.trig;

import lab.arahnik.math.trig.ext.Tan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class TanTest {

    private static Stream<Double> checkPath() {
        return Stream.iterate(0.01, x -> x + 0.01)
            .limit((int) ((Math.PI / 2) / 0.01));
    }

    @ParameterizedTest
    @MethodSource("checkPath")
    void testTanPathValues(double x) {
        Tan tan = new Tan();
        assertEquals(Math.tan(x), tan.calculate(BigDecimal.valueOf(x), BigDecimal.valueOf(1e-6)).doubleValue(), 1e-6);
    }

    @Test
    public void testTanZero() {
        Tan tan = new Tan();
        assertEquals(0.0, tan.calculate(BigDecimal.ZERO, BigDecimal.valueOf(1e-6)).doubleValue(), 1e-6);
    }

    @Test
    public void testTanPiOverTwo() {
        Tan tan = new Tan();
        try {
            double result = tan.calculate(BigDecimal.valueOf(Math.PI / 2), BigDecimal.valueOf(1e-6)).doubleValue();
            assertTrue(Double.isNaN(result));
        } catch (ArithmeticException e) {
            assertTrue(true, "Expected ArithmeticException for tan(pi/2)");
        }
    }

    @Test
    public void testTanNearPiOverTwo() {
        Tan tan = new Tan();
        double result = tan.calculate(BigDecimal.valueOf(Math.PI / 2 - 1e-6), BigDecimal.valueOf(1e-6)).doubleValue();
        assertTrue(Double.isFinite(result));
    }

    @Test
    public void testTanNegativePiOverTwo() {
        Tan tan = new Tan();
        assertThrows(ArithmeticException.class, () ->
            tan.calculate(BigDecimal.valueOf(-Math.PI / 2), BigDecimal.valueOf(1e-6))
        );
    }
}
