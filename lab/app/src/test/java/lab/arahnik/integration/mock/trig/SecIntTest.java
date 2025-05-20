package lab.arahnik.integration.mock.trig;

import lab.arahnik.math.trig.ext.Sec;
import lab.arahnik.math.trig.Cos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecIntTest {
    @Mock
    private Cos mockCos;

    @Test
    void testSecWithMockedCos() {
        BigDecimal input = new BigDecimal("1.0");
        BigDecimal acc = new BigDecimal("0.00001");
        BigDecimal cosValue = new BigDecimal("0.5");
        when(mockCos.calculate(input, acc)).thenReturn(cosValue);

        Sec sec = new Sec(mockCos);

        BigDecimal result = sec.calculate(input, acc);

        BigDecimal expected = BigDecimal.ONE.divide(cosValue, acc.scale() + 5, RoundingMode.HALF_UP)
                .setScale(acc.scale(), RoundingMode.HALF_UP);
        assertEquals(expected, result);
        verify(mockCos, times(1)).calculate(input, acc);
    }

    @Test
    void testSecReturnsNullWhenCosIsZero() {
        BigDecimal input = new BigDecimal("0.0");
        BigDecimal acc = new BigDecimal("0.00001");
        when(mockCos.calculate(input, acc)).thenReturn(BigDecimal.ZERO);

        Sec sec = new Sec(mockCos);

        BigDecimal result = sec.calculate(input, acc);

        assertNull(result);
        verify(mockCos, times(1)).calculate(input, acc);
    }
}
