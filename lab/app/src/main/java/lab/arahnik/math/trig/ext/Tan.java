package lab.arahnik.math.trig.ext;

import lab.arahnik.math.MathModule;
import lab.arahnik.math.trig.Cos;
import lab.arahnik.math.trig.Sin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Tan implements MathModule {
    private final Sin sin;
    private final Cos cos;

    public Tan() {
        this.sin = new Sin();
        this.cos = new Cos(sin);
    }

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal acc) {
        MathContext mc = new MathContext(acc.scale() + 5, RoundingMode.HALF_UP);

        BigDecimal s = sin.calculate(x, acc);
        BigDecimal c = cos.calculate(x, acc);

        if (c.abs().compareTo(acc) < 1) {
            throw new ArithmeticException("Tangent undefined for this input (cos(x) too close to zero)");
        }

        return s.divide(c, mc);
    }
}
