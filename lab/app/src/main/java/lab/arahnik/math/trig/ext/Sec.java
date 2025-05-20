package lab.arahnik.math.trig.ext;

import lab.arahnik.math.MathModule;
import lab.arahnik.math.trig.Cos;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Sec implements MathModule {
    private final Cos cos;

    public Sec() {
        this.cos = new Cos();
    }

    public Sec(Cos cos) {
        this.cos = cos;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal acc) {
        BigDecimal cosValue = cos.calculate(x, acc);
        if (cosValue.abs().compareTo(acc) < 1) {
            return null;
        }
        MathContext mc = new MathContext(acc.scale() + 5, RoundingMode.HALF_UP);
        return BigDecimal.ONE.divide(cosValue, mc).setScale(acc.scale(), RoundingMode.HALF_UP);
    }
}
