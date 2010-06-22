package org.fenwaygrp.fenmarking;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MetricResult {

	private BigDecimal arithmeticVelocity;
    private BigDecimal geometricVelocity;
    private BigDecimal harmonicVelocity;
    private BigDecimal arithmeticVelocityStdDev;
    private BigDecimal geometricVelocityStdDev;
    
    private BigDecimal throughput;
    private BigDecimal median;
    private List<Long> modes;
    private BigDecimal highestValue;
	
    private StatisticCalculator calculator = new StatisticCalculatorDefaultImpl();
	
	public MetricResult(List<Long> values) {
		this.arithmeticVelocity = calculator.getArithmeticMean(values);
		this.geometricVelocity = calculator.getGeometricMean(values);
		this.harmonicVelocity = calculator.getHarmonicMean(values);
		this.arithmeticVelocityStdDev = calculator.getArithmeticMeanStdDev(values);
        this.geometricVelocityStdDev = calculator.getGeometricMeanStdDev(values);
		this.modes = calculator.getMode(values);
		this.median = calculator.getMedian(values);
        
        this.throughput = calculateThroughPut(values);
        this.highestValue = calculateHighestValue(values);
	}

	private BigDecimal calculateThroughPut(List<Long> values){
	    FenmarkBigDecimal throughPut = new FenmarkBigDecimal(0);
	    for(Long item : values){
	        throughPut = throughPut.add(item);
	    }
	    return new FenmarkBigDecimal(values.size()).divide(throughPut).toBigDecimal(2, RoundingMode.HALF_EVEN);
	}

	private BigDecimal calculateHighestValue(List<Long> values){
	    Long highestValue = 0L;
	    for(Long item : values){
	        if(item > highestValue){
	            highestValue = item;
	        }
	    }
	    return new FenmarkBigDecimal(highestValue).toBigDecimal(2, RoundingMode.HALF_EVEN);
	}
	
	/*Setter/Getter*/
    public BigDecimal getArithmeticVelocity() {
        return arithmeticVelocity;
    }

    public BigDecimal getGeometricVelocity() {
        return geometricVelocity;
    }

    public BigDecimal getHarmonicVelocity() {
        return harmonicVelocity;
    }

    public BigDecimal getArithmeticVelocityStdDev() {
        return arithmeticVelocityStdDev;
    }

    public BigDecimal getGeometricVelocityStdDev() {
        return geometricVelocityStdDev;
    }

    public BigDecimal getThroughput() {
        return throughput;
    }

    public BigDecimal getMedian() {
        return median;
    }

    public List<Long> getModes() {
        return modes;
    }

    public BigDecimal getHighestValue() {
        return highestValue;
    }

    public StatisticCalculator getCalculator() {
        return calculator;
    }

}
