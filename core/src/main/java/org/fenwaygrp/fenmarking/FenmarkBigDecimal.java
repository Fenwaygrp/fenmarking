package org.fenwaygrp.fenmarking;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

class FenmarkBigDecimal {

    private static final int scale = 6;
    private static final RoundingMode rmode = RoundingMode.HALF_EVEN;
    private static final MathContext mathContext = new MathContext(scale, rmode);

    private BigDecimal target;

    /* C O N S T R U C T O R S */
    public FenmarkBigDecimal() {
        this(0);
    }

    public FenmarkBigDecimal(Long value) {
        target = BigDecimal.valueOf(value);
        target = target.setScale(scale, rmode);
    }

    public FenmarkBigDecimal(Integer value) {
        this(value.longValue());
    }

    public FenmarkBigDecimal(Double value) {
        target = BigDecimal.valueOf(value);
        target = target.setScale(scale, rmode);
    }

    public FenmarkBigDecimal(BigDecimal value) {
        target = new BigDecimal(value.toString(), mathContext);
        target = target.setScale(scale, rmode);
    }

    public FenmarkBigDecimal(String value) {
        target = new BigDecimal(value, mathContext);
        target = target.setScale(scale, rmode);
    }

    /* A D D I T I O N O P E R A T I O N S */
    public FenmarkBigDecimal add(FenmarkBigDecimal augend) {
        return new FenmarkBigDecimal(target.add(augend.toBigDecimal(), mathContext));
    }

    public FenmarkBigDecimal add(Long augend) {
        return add(new FenmarkBigDecimal(augend));
    }

    public FenmarkBigDecimal add(double augend) {
        return add(new FenmarkBigDecimal(augend));
    }

    /* D I V I S I O N O P E R A T I O N S */
    public FenmarkBigDecimal divide(FenmarkBigDecimal divisor) {
        return new FenmarkBigDecimal(target.divide(divisor.target, mathContext));
    }

    public FenmarkBigDecimal divide(Integer divisor) {
        return divide(new FenmarkBigDecimal(divisor));
    }

    public FenmarkBigDecimal divide(Long divisor) {
        return divide(new FenmarkBigDecimal(divisor));
    }

    /* S U B T R A C T I O N O P E R A T I O N S */
    public FenmarkBigDecimal subtract(FenmarkBigDecimal subtrahend) {
        return new FenmarkBigDecimal(target.subtract(subtrahend.toBigDecimal(), mathContext));
    }

    /* M I S C O P E R A T I O N S */
    public BigDecimal toBigDecimal() {
        return target;
    }

    public BigDecimal toBigDecimal(int scale, RoundingMode roundingMode) {
        return target.setScale(scale, roundingMode);
    }

    public double doubleValue() {
        return target.doubleValue();
    }

}
