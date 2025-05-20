package lab.arahnik.unit.log;

import lab.arahnik.math.log.Ln;
import lab.arahnik.math.log.ext.Log;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class LogTest {

    @Test
    public void testLnPositiveInput() {
        Ln ln = new Ln();
        double result = ln.calculate(new BigDecimal(Math.E), new BigDecimal("1e-10")).doubleValue();
        assertEquals(1, result, 1e-10);
    }

    @Test
    public void testLnSmallInput() {
        Ln ln = new Ln();
        double result = ln.calculate(new BigDecimal("1.0001"), new BigDecimal("0.0001")).doubleValue();
        assertEquals(0.0001, result, 0.0001);
    }

    @Test
    public void testLnThrowsExceptionForNonPositiveInput() {
        Ln ln = new Ln();
        assertThrows(IllegalArgumentException.class, () -> ln.calculate(BigDecimal.ZERO, new BigDecimal("0.0001")));
        assertThrows(IllegalArgumentException.class, () -> ln.calculate(new BigDecimal("-1"), new BigDecimal("0.0001")));
    }

    @Test
    public void testLnHighAccuracy() {
        Ln ln = new Ln();
        double result = ln.calculate(new BigDecimal(Math.E), new BigDecimal("1e-10")).doubleValue();
        assertEquals(1, result, 1e-10);
    }

    @Test
    public void testLogBase10() {
        Log log = new Log(new BigDecimal("10"));
        double result = log.calculate(new BigDecimal("100"), new BigDecimal("0.0001")).doubleValue();
        assertEquals(2, result, 0.001);
    }

    @Test
    public void testLogBase2() {
        Log log = new Log(new BigDecimal("2"));
        double result = log.calculate(new BigDecimal("8"), new BigDecimal("0.0001")).doubleValue();
        assertEquals(3, result, 0.001);
    }

    @Test
    public void testLogBaseE() {
        Ln ln = new Ln();
        Log log = new Log(new BigDecimal(Math.E), ln);
        double result = log.calculate(new BigDecimal(Math.E * Math.E), new BigDecimal("0.0001")).doubleValue();
        assertEquals(2, result, 0.001);
    }

    @Test
    public void testLogThrowsExceptionForNonPositiveInput() {
        Log log = new Log(new BigDecimal("10"));
        assertThrows(IllegalArgumentException.class, () -> log.calculate(BigDecimal.ZERO, new BigDecimal("0.0001")));
        assertThrows(IllegalArgumentException.class, () -> log.calculate(new BigDecimal("-1"), new BigDecimal("0.0001")));
    }

    @Test
    public void testLogWithCustomLn() {
        Ln customLn = new Ln();
        Log log = new Log(new BigDecimal("5"), customLn);
        double result = log.calculate(new BigDecimal("25"), new BigDecimal("0.0001")).doubleValue();
        assertEquals(2, result, 0.001);
    }

    @Test
    public void testLogHighAccuracy() {
        Log log = new Log(new BigDecimal("10"));
        double result = log.calculate(new BigDecimal("1000"), new BigDecimal("1e-10")).doubleValue();
        assertEquals(3, result, 1e-9);
    }
}
