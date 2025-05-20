package lab.arahnik.unit.trig;

import lab.arahnik.math.trig.ext.Sec;
import lab.arahnik.math.trig.Cos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SecTest {

    private static final double PI = Math.PI;
    private final Sec sec = new Sec();


    @ParameterizedTest
    @ValueSource(doubles = {0, PI / 3, PI / 4, -PI / 3, -PI / 4})
    void testSecTableValues(double x) {
        assertEquals(1 / Math.cos(x), sec.calculate(BigDecimal.valueOf(x), BigDecimal.valueOf(1e-10)).doubleValue(), 1e-10);
    }

    @Test
    void testSecZero() {
        assertEquals(1, sec.calculate(BigDecimal.ZERO, BigDecimal.valueOf(1e-10)).doubleValue(), 1e-10);
    }

    @Test
    void testSecPiOverTwo() {
        assertNull(sec.calculate(BigDecimal.valueOf(PI / 2), BigDecimal.valueOf(1e-10)));
    }

    @Test
    void testSecNegativePiOverTwo() {
        assertNull(sec.calculate(BigDecimal.valueOf(-PI / 2), BigDecimal.valueOf(1e-10)));
    }

    @Test
    void testSecCustomCos() {
        Cos mockCos = new Cos() {
            @Override
            public BigDecimal calculate(BigDecimal x, BigDecimal acc) {
                return BigDecimal.valueOf(0.5);
            }
        };

        Sec customSec = new Sec(mockCos);
        assertEquals(2.0, customSec.calculate(BigDecimal.ZERO, BigDecimal.valueOf(1e-10)).doubleValue(), 1e-10);
    }

    @Test
    void testSecHighAccuracy() {
        double x = PI / 4;
        assertEquals(1 / Math.cos(x), sec.calculate(BigDecimal.valueOf(x), BigDecimal.valueOf(1e-15)).doubleValue(), 1e-15);
    }
}
