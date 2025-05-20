package lab.arahnik.integration.mock.trig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import lab.arahnik.math.trig.Sin;
import lab.arahnik.math.trig.ext.Tan;
import lab.arahnik.math.trig.Cos;
import java.math.RoundingMode;

@ExtendWith(MockitoExtension.class)
public class TanIntTest {

    @Mock
    Sin mockSin;

    @Mock
    Cos mockCos;

    @Test
    void testCalculate_DelegatesToSinAndCos() {
        BigDecimal x = new BigDecimal("1.2345");
        BigDecimal acc = new BigDecimal("0.00001");
        BigDecimal sinVal = new BigDecimal("0.944");
        BigDecimal cosVal = new BigDecimal("0.329");

        when(mockSin.calculate(x, acc)).thenReturn(sinVal);
        when(mockCos.calculate(x, acc)).thenReturn(cosVal);

        Tan tan = new Tan(mockSin, mockCos);

        BigDecimal result = tan.calculate(x, acc);

        verify(mockSin, atLeastOnce()).calculate(x, acc);
        verify(mockCos, atLeastOnce()).calculate(x, acc);
        BigDecimal expected = sinVal.divide(cosVal, acc.scale() + 5, RoundingMode.HALF_UP);
        assertEquals(1, result.compareTo(expected));
    }

    @Test
    void testCalculate_CosTooCloseToZero_ThrowsException() {
        BigDecimal x = new BigDecimal("2.0");
        BigDecimal acc = new BigDecimal("0.00001");
        BigDecimal sinVal = new BigDecimal("0.5");
        BigDecimal cosVal = new BigDecimal("0.000001"); 

        when(mockSin.calculate(x, acc)).thenReturn(sinVal);
        when(mockCos.calculate(x, acc)).thenReturn(cosVal);

        Tan tan = new Tan(mockSin, mockCos);

        assertThrows(ArithmeticException.class, () -> tan.calculate(x, acc));
    }
}
