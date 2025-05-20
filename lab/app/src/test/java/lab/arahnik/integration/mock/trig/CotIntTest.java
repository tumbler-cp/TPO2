package lab.arahnik.integration.mock.trig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import lab.arahnik.math.trig.Sin;
import lab.arahnik.math.trig.ext.Cot;
import lab.arahnik.math.trig.Cos;
import java.math.RoundingMode;

@ExtendWith(MockitoExtension.class)
public class CotIntTest {

    @Mock
    Sin sinMock;

    @Mock
    Cos cosMock;

    @Test
    void testCalculate_usesSinAndCos() {
        BigDecimal x = new BigDecimal("1.0");
        BigDecimal acc = new BigDecimal("0.00001");
        BigDecimal sinValue = new BigDecimal("0.84147");
        BigDecimal cosValue = new BigDecimal("0.54030");

        when(sinMock.calculate(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(sinValue);
        when(cosMock.calculate(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(cosValue);

        Cot cot = new Cot(sinMock, cosMock);
        BigDecimal result = cot.calculate(x, acc);

        verify(sinMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        verify(cosMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));

        BigDecimal expected = cosValue.divide(sinValue, new java.math.MathContext(acc.scale() + 5, RoundingMode.HALF_UP));
        assertEquals(0, expected.compareTo(result));
    }

    @Test
    void testCalculate_throwsExceptionWhenSinIsZero() {
        BigDecimal x = new BigDecimal("0");
        BigDecimal acc = new BigDecimal("0.00001");
        BigDecimal sinValue = new BigDecimal("0.0000001"); 
        BigDecimal cosValue = new BigDecimal("1");

        when(sinMock.calculate(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(sinValue);
        when(cosMock.calculate(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(cosValue);

        Cot cot = new Cot(sinMock, cosMock);

        assertThrows(ArithmeticException.class, () -> cot.calculate(x, acc));
    }

    @Test
    void testCalculate_withNegativeInput() {
        BigDecimal x = new BigDecimal("-1.0");
        BigDecimal acc = new BigDecimal("0.00001");
        BigDecimal sinValue = new BigDecimal("-0.84147");
        BigDecimal cosValue = new BigDecimal("0.54030");

        org.mockito.Mockito.when(sinMock.calculate(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(sinValue);
        org.mockito.Mockito.when(cosMock.calculate(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(cosValue);

        Cot cot = new Cot(sinMock, cosMock);
        BigDecimal result = cot.calculate(x, acc);

        BigDecimal expected = cosValue.divide(sinValue, new java.math.MathContext(acc.scale() + 5, RoundingMode.HALF_UP));
        org.junit.jupiter.api.Assertions.assertEquals(0, expected.compareTo(result));
    }
}
