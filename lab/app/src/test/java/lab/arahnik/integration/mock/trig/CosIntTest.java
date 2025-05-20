package lab.arahnik.integration.mock.trig;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lab.arahnik.math.trig.Cos;
import lab.arahnik.math.trig.Sin;

@ExtendWith(MockitoExtension.class)
public class CosIntTest {

    @Mock
    Sin sinMock;

    @Test
    void testCosUsesSin() {
        Cos cos = new Cos(sinMock);
        BigDecimal x = new BigDecimal("666");
        BigDecimal acc = new BigDecimal("1e-6");

        cos.calculate(x, acc);

        verify(sinMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    @Test
    void testCosWithRealSin() {
        Sin realSin = new Sin();
        Cos cos = new Cos(realSin);

        BigDecimal x = new BigDecimal("0");
        BigDecimal acc = new BigDecimal("1e-6");

        BigDecimal result = cos.calculate(x, acc);

        BigDecimal expected = BigDecimal.ONE;
        BigDecimal delta = new BigDecimal("1e-6");
        assert(result.subtract(expected).abs().compareTo(delta) < 0);
    }
}
