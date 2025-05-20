package lab.arahnik.math.function;

import lab.arahnik.math.MathModule;
import lab.arahnik.math.log.Ln;
import lab.arahnik.math.log.ext.Log;
import lab.arahnik.math.trig.Cos;
import lab.arahnik.math.trig.Sin;
import lab.arahnik.math.trig.ext.Cot;
import lab.arahnik.math.trig.ext.Sec;
import lab.arahnik.math.trig.ext.Tan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Function implements MathModule {
    private final Sin sin;
    private final Cos cos;
    private final Cot cot;
    private final Tan tan;
    private final Sec sec;

    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log5;
    private final Log log10;

    public Function() {
        this.sin = new Sin();
        this.cos = new Cos(sin);
        this.cot = new Cot(sin, cos);
        this.tan = new Tan(sin, cos);
        this.sec = new Sec(cos);

        this.ln = new Ln();
        this.log2 = new Log(BigDecimal.valueOf(2), ln);
        this.log3 = new Log(BigDecimal.valueOf(3), ln);
        this.log5 = new Log(BigDecimal.valueOf(5), ln);
        this.log10 = new Log(BigDecimal.valueOf(10), ln);
    }

    public Function(Sin sin, Cos cos, Ln ln) {
        this.sin = sin;
        this.cos = cos;
        this.cot = new Cot(sin, cos);
        this.tan = new Tan(sin, cos);
        this.sec = new Sec(cos);

        this.ln = ln;
        this.log2 = new Log(BigDecimal.valueOf(2), ln);
        this.log3 = new Log(BigDecimal.valueOf(3), ln);
        this.log5 = new Log(BigDecimal.valueOf(5), ln);
        this.log10 = new Log(BigDecimal.valueOf(10), ln);
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal acc) {
        if (x.compareTo(BigDecimal.ZERO) <= 0) {
            // x <= 0 : ((((cot(x) * tan(x)) - tan(x)) - (sec(x) * (sin(x) ^ 3))) - tan(x))
            BigDecimal cotX = cot.calculate(x, acc);
            BigDecimal tanX = tan.calculate(x, acc);
            BigDecimal secX = sec.calculate(x, acc);
            BigDecimal sinX = sin.calculate(x, acc);

            BigDecimal sinX3 = sinX.pow(3);
            BigDecimal part1 = cotX.multiply(tanX);
            BigDecimal part2 = part1.subtract(tanX);
            BigDecimal part3 = secX.multiply(sinX3);
            BigDecimal part4 = part2.subtract(part3);
            BigDecimal result = part4.subtract(tanX);

            return result.setScale(acc.scale(), RoundingMode.HALF_UP);
        } else {
            // x > 0 : (((((ln(x) ^ 2) ^ 3) / ((log_2(x) / log_5(x)) ^ 2)) + log_3(x)) / (log_3(x) * ((log_2(x) * log_10(x)) / log_5(x))))
            BigDecimal lnX = ln.calculate(x, acc);
            BigDecimal log2X = log2.calculate(x, acc);
            BigDecimal log3X = log3.calculate(x, acc);
            BigDecimal log5X = log5.calculate(x, acc);
            BigDecimal log10X = log10.calculate(x, acc);

            BigDecimal lnX2 = lnX.pow(2);
            BigDecimal lnX2_3 = lnX2.pow(3);

            BigDecimal log2Xdlog5X = log2X.divide(log5X, acc.scale() + 5, RoundingMode.HALF_UP);
            BigDecimal log2Xdlog5X_2 = log2Xdlog5X.pow(2);

            BigDecimal numerator = lnX2_3.divide(log2Xdlog5X_2, acc.scale() + 5, RoundingMode.HALF_UP).add(log3X);

            BigDecimal log2Xmlog10X = log2X.multiply(log10X);
            BigDecimal denominator = log3X.multiply(log2Xmlog10X.divide(log5X, acc.scale() + 5, RoundingMode.HALF_UP));

            BigDecimal result = numerator.divide(denominator, acc.scale(), RoundingMode.HALF_UP);

            return result;
        }
    }
}
