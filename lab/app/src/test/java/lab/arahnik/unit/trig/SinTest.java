package lab.arahnik.unit.trig;

import lab.arahnik.math.trig.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SinTest {

    private static final double PI = Math.PI;
    private final Sin sin = new Sin();

    private static Stream<Double> checkPath() {
        return Stream.iterate(-2 * PI, x -> x + 0.1)
                .limit((int) ((4 * PI) / 0.1));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, PI / 6, PI / 4, PI / 3, PI / 2, PI, -PI / 2, -PI})
    void testSinTableValues(double x) {
        assertAll(() -> assertEquals(Math.sin(x), sin.calculate(BigDecimal.valueOf(x), BigDecimal.valueOf(1e-10)).doubleValue(), 1e-10));
    }

    @ParameterizedTest
    @MethodSource("checkPath")
    void testSinPathValues(double x) {
        assertEquals(Math.sin(x), sin.calculate(BigDecimal.valueOf(x), BigDecimal.valueOf(1e-10)).doubleValue(), 1e-10);
    }

    @Test
    void testSinHighAccuracy() {
        double x = PI / 4;
        assertEquals(Math.sin(x), sin.calculate(BigDecimal.valueOf(x), BigDecimal.valueOf(1e-15)).doubleValue(), 1e-15);
    }

    @Test
    void testSinZero() {
        assertEquals(0, sin.calculate(BigDecimal.valueOf(0), BigDecimal.valueOf(1e-10)).doubleValue(), 1e-10);
    }
}
