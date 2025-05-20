package lab.arahnik.math.trig;

import lab.arahnik.math.MathModule;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cos implements MathModule {
    private final Sin sin;

    public Cos () {
        this.sin = new Sin();
    }

    public Cos (Sin sin) {
        this.sin = sin;
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal acc) {
        BigDecimal pi = new BigDecimal(Math.PI, new java.math.MathContext(acc.scale() + 5));
        BigDecimal halfPi = pi.divide(BigDecimal.valueOf(2), acc.scale() + 5, RoundingMode.HALF_UP);
        return sin.calculate(halfPi.subtract(x), acc);
    }
}
