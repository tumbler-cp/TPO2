package lab.arahnik.math.trig;

import lab.arahnik.math.MathModule;

import java.math.BigDecimal;
import java.math.MathContext;

public class Sin extends TrigMathModule implements MathModule {
    public BigDecimal calculate(BigDecimal x, BigDecimal acc) {
        x = normalizeAngle(x);

        BigDecimal term = x, sum = x;
        MathContext mc = new MathContext(acc.scale() + 2);

        long n = 1;

        while (term.abs().compareTo(acc) > 0) {
            BigDecimal numerator = term.multiply(x).multiply(x).negate();
            BigDecimal denominator = BigDecimal.valueOf((2L * n) * (2L * n + 1));
            term = numerator.divide(denominator, mc);

            sum = sum.add(term);
            n++;

            if (n > 1.0 / acc.doubleValue()) {
                break;
            }
        }

        return sum;
    }
}
