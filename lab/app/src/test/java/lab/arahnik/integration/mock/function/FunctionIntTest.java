package lab.arahnik.integration.mock.function;

import lab.arahnik.math.function.Function;
import lab.arahnik.math.trig.Sin;
import lab.arahnik.math.trig.Cos;
import lab.arahnik.math.log.Ln;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class FunctionIntTest {

    @Test
    void testFuncUsesSin() {
        Sin sin = Mockito.mock(Sin.class);
        Cos cos = Mockito.mock(Cos.class);
        Ln ln = Mockito.mock(Ln.class);

        BigDecimal x = BigDecimal.valueOf(-1);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        Mockito.when(sin.calculate(Mockito.eq(x), Mockito.eq(acc))).thenReturn(BigDecimal.valueOf(0.5));
        Mockito.when(cos.calculate(Mockito.eq(x), Mockito.eq(acc))).thenReturn(BigDecimal.valueOf(0.8));

        Function function = new Function(sin, cos, ln);

        function.calculate(x, acc);
        Mockito.verify(sin, Mockito.atLeastOnce()).calculate(Mockito.eq(x), Mockito.eq(acc));
    }

    @Test
    void testFuncUsesRealSin() {
        Function function = new Function();
        BigDecimal x = BigDecimal.valueOf(-1);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        BigDecimal result = function.calculate(x, acc);
        Assertions.assertNotNull(result);
    }

    @Test
    void testFuncUsesTan() {
        Sin sin = Mockito.mock(Sin.class);
        Cos cos = Mockito.mock(Cos.class);
        Ln ln = Mockito.mock(Ln.class);

        BigDecimal x = BigDecimal.valueOf(-2);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        Mockito.when(sin.calculate(Mockito.eq(x), Mockito.eq(acc))).thenReturn(BigDecimal.valueOf(0.5));
        Mockito.when(cos.calculate(Mockito.eq(x), Mockito.eq(acc))).thenReturn(BigDecimal.valueOf(0.5));

        Function function = new Function(sin, cos, ln);

        function.calculate(x, acc);
        Mockito.verify(sin, Mockito.atLeastOnce()).calculate(Mockito.eq(x), Mockito.eq(acc));
        Mockito.verify(cos, Mockito.atLeastOnce()).calculate(Mockito.eq(x), Mockito.eq(acc));
    }

    @Test
    void testFuncUsesRealTan() {
        Function function = new Function();
        BigDecimal x = BigDecimal.valueOf(-Math.PI / 4);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        BigDecimal result = function.calculate(x, acc);
        Assertions.assertNotNull(result);
    }

    @Test
    void testFuncUsesCot() {
        Sin sin = Mockito.mock(Sin.class);
        Cos cos = Mockito.mock(Cos.class);
        Ln ln = Mockito.mock(Ln.class);

        BigDecimal x = BigDecimal.valueOf(-3);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        Mockito.when(sin.calculate(Mockito.eq(x), Mockito.eq(acc))).thenReturn(BigDecimal.valueOf(0.5));
        Mockito.when(cos.calculate(Mockito.eq(x), Mockito.eq(acc))).thenReturn(BigDecimal.valueOf(0.5));

        Function function = new Function(sin, cos, ln);

        function.calculate(x, acc);
        Mockito.verify(sin, Mockito.atLeastOnce()).calculate(Mockito.eq(x), Mockito.eq(acc));
        Mockito.verify(cos, Mockito.atLeastOnce()).calculate(Mockito.eq(x), Mockito.eq(acc));
    }

    @Test
    void testFuncUsesRealCot() {
        Function function = new Function();
        BigDecimal x = BigDecimal.valueOf(-Math.PI / 4);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        BigDecimal result = function.calculate(x, acc);
        Assertions.assertNotNull(result);
    }

    @Test
    void testFuncUsesSec() {
        Sin sin = Mockito.mock(Sin.class);
        Cos cos = Mockito.mock(Cos.class);
        Ln ln = Mockito.mock(Ln.class);

        BigDecimal x = BigDecimal.valueOf(-0.5);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        Mockito.when(sin.calculate(Mockito.eq(x), Mockito.eq(acc))).thenReturn(BigDecimal.valueOf(0.5));
        Mockito.when(cos.calculate(Mockito.eq(x), Mockito.eq(acc))).thenReturn(BigDecimal.valueOf(0.8));

        Function function = new Function(sin, cos, ln);

        function.calculate(x, acc);
        Mockito.verify(cos, Mockito.atLeastOnce()).calculate(Mockito.eq(x), Mockito.eq(acc));
    }

    @Test
    void testFuncUsesRealSec() {
        Function function = new Function();
        BigDecimal x = BigDecimal.valueOf(-Math.PI / 3);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        // sec(-pi/3) = 2
        BigDecimal result = function.calculate(x, acc);
        Assertions.assertNotNull(result);
    }

    @Test
    void testFuncUsesRealLn() {
        Function function = new Function();
        BigDecimal x = BigDecimal.valueOf(2);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        BigDecimal result = function.calculate(x, acc);
        Assertions.assertNotNull(result);
    }

    @Test
    void testFuncUsesRealLog() {
        Function function = new Function();
        BigDecimal x = BigDecimal.valueOf(8);
        BigDecimal acc = BigDecimal.valueOf(1e-5);

        BigDecimal result = function.calculate(x, acc);
        Assertions.assertNotNull(result);

        BigDecimal log2 = BigDecimal.valueOf(Math.log(8) / Math.log(2));
        BigDecimal log3 = BigDecimal.valueOf(Math.log(8) / Math.log(3));
        BigDecimal log5 = BigDecimal.valueOf(Math.log(8) / Math.log(5));
        BigDecimal log10 = BigDecimal.valueOf(Math.log(8) / Math.log(10));

        Assertions.assertEquals(Math.log(8) / Math.log(2), log2.doubleValue(), 1e-3);
        Assertions.assertEquals(Math.log(8) / Math.log(3), log3.doubleValue(), 1e-3);
        Assertions.assertEquals(Math.log(8) / Math.log(5), log5.doubleValue(), 1e-3);
        Assertions.assertEquals(Math.log(8) / Math.log(10), log10.doubleValue(), 1e-3);
    }
}
