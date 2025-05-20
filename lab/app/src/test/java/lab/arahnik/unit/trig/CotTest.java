package lab.arahnik.unit.trig;

import lab.arahnik.math.trig.ext.Cot;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CotTest {

    @Test
    public void testCotZero() {
        Cot cot = new Cot();
        assertThrows(ArithmeticException.class, () -> cot.calculate(BigDecimal.ZERO, new BigDecimal("1e-6")));
    }

    @Test
    public void testCotPiOverTwo() {
        Cot cot = new Cot();
        assertEquals(0.0, cot.calculate(new BigDecimal(Math.PI).divide(new BigDecimal(2)), new BigDecimal("1e-6")).doubleValue(), 1e-6);
    }

    @Test
    public void testCotNearZero() {
        Cot cot = new Cot();
        assertThrows(ArithmeticException.class, () -> cot.calculate(new BigDecimal("1e-6"), new BigDecimal("1e-6")));
    }

    @Test
    public void testCotNegativePiOverTwo() {
        Cot cot = new Cot();
        assertEquals(0.0, cot.calculate(new BigDecimal(-Math.PI).divide(new BigDecimal(2)), new BigDecimal("1e-6")).doubleValue(), 1e-6);
    }
}
