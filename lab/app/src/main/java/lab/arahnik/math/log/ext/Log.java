package lab.arahnik.math.log.ext;

import lab.arahnik.math.MathModule;
import lab.arahnik.math.log.Ln;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Log implements MathModule {
    private final Ln ln;
    private final BigDecimal base;

    public Log(double base) {
        this(new BigDecimal(base), new Ln());
    }

    public Log(BigDecimal base) {
        this(base, new Ln());
    }

    public Log(BigDecimal base, Ln ln) {
        if (base.compareTo(BigDecimal.ZERO) <= 0 || base.compareTo(BigDecimal.ONE) == 0) {
            throw new IllegalArgumentException("Base must be > 0 and != 1");
        }
        this.base = base;
        this.ln = ln;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal acc) {
        if (x.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("x must be > 0");
        }
        MathContext mc = new MathContext(acc.scale() > 0 ? acc.scale() * 2 : 30, RoundingMode.HALF_UP);
        BigDecimal lnX = ln.calculate(x, acc);
        BigDecimal lnBase = ln.calculate(base, acc);
        return lnX.divide(lnBase, mc);
    }
}
