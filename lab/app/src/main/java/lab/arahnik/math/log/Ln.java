package lab.arahnik.math.log;

import lab.arahnik.math.MathModule;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Ln implements MathModule {
    private static final int SCALE = 30;

    public BigDecimal calculate(BigDecimal x, BigDecimal acc) {
        if (x.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("x must be > 0");
        }

        MathContext mc = new MathContext(SCALE, RoundingMode.HALF_UP);

        BigDecimal one = BigDecimal.ONE;
        BigDecimal y = x.subtract(one).divide(x.add(one), mc);
        BigDecimal y2 = y.multiply(y, mc);

        BigDecimal term = y;
        BigDecimal sum = BigDecimal.ZERO;
        int n = 1;

        BigDecimal two = new BigDecimal("2");
        BigDecimal accAbs = acc.abs();

        while (term.abs().compareTo(accAbs) > 0) {
            BigDecimal denom = new BigDecimal(2 * n - 1);
            sum = sum.add(term.divide(denom, mc));
            term = term.multiply(y2, mc);
            n++;
        }

        return sum.multiply(two, mc);
    }
}
