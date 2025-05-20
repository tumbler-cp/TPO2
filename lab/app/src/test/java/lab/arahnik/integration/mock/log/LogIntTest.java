package lab.arahnik.integration.mock.log;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lab.arahnik.math.log.Ln;



@ExtendWith(MockitoExtension.class)
public class LogIntTest {

    @Mock
    Ln lnMock;

    @org.junit.jupiter.api.Test
    void testLogUsesLn() {
        lab.arahnik.math.log.ext.Log log = new lab.arahnik.math.log.ext.Log(new java.math.BigDecimal("2"), lnMock);
        java.math.BigDecimal x = new java.math.BigDecimal("8");
        java.math.BigDecimal acc = new java.math.BigDecimal("1e-6");

        when(lnMock.calculate(org.mockito.ArgumentMatchers.any(java.math.BigDecimal.class), org.mockito.ArgumentMatchers.any(java.math.BigDecimal.class)))
            .thenReturn(new java.math.BigDecimal("1"));

        log.calculate(x, acc);

        org.mockito.Mockito.verify(lnMock, org.mockito.Mockito.atLeastOnce())
            .calculate(org.mockito.ArgumentMatchers.any(java.math.BigDecimal.class), org.mockito.ArgumentMatchers.any(java.math.BigDecimal.class));
    }

    @org.junit.jupiter.api.Test
    void testLogWithRealLn() {
        Ln realLn = new Ln();
        lab.arahnik.math.log.ext.Log log = new lab.arahnik.math.log.ext.Log(new java.math.BigDecimal("2"), realLn);

        java.math.BigDecimal x = new java.math.BigDecimal("8");
        java.math.BigDecimal acc = new java.math.BigDecimal("1e-6");

        java.math.BigDecimal result = log.calculate(x, acc);

        java.math.BigDecimal expected = new java.math.BigDecimal("3");
        java.math.BigDecimal delta = new java.math.BigDecimal("1e-5");
        assert(result.subtract(expected).abs().compareTo(delta) < 0);
    }
}
