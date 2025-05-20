package lab.arahnik.math.trig;

import java.math.BigDecimal;

public abstract class TrigMathModule {
    private final BigDecimal PI2 = BigDecimal.valueOf(Math.PI * 2);

    protected BigDecimal normalizeAngle(BigDecimal x) {
        if (x.compareTo(BigDecimal.ZERO) >= 0) {
            while (x.compareTo(PI2) > 0) {
                x = x.subtract(PI2);
            }
        } else {
            while (x.compareTo(PI2.negate()) < 0) {
                x = x.add(PI2);
            }
        }
        return x;
    }
}
