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
	
	public MetricResult(List<Long> values, Long totalExecutionTime) {
		this.arithmeticVelocity = calculator.getArithmeticMean(values);
		this.geometricVelocity = calculator.getGeometricMean(values);
		this.harmonicVelocity = calculator.getHarmonicMean(values);
		this.arithmeticVelocityStdDev = calculator.getArithmeticMeanStdDev(values);
        this.geometricVelocityStdDev = calculator.getGeometricMeanStdDev(values);
		this.modes = calculator.getMode(values);
		this.median = calculator.getMedian(values);
        
        this.throughput = calculateTps(totalExecutionTime, values.size());
        this.highestValue = calculateHighestValue(values);
	}

	private BigDecimal calculateTps(Long totalExecutionTime, Integer numberOfTransactions){
	    FenmarkBigDecimal tps = new FenmarkBigDecimal(totalExecutionTime);
	    tps = tps.divide(numberOfTransactions);
	    return tps.toBigDecimal();
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
	
	public BigDecimal getMeanDurationPerTransaction(MeanType mean){
		BigDecimal value = null;
		if(mean.equals(MeanType.ARITHMETIC)){
			value = arithmeticVelocity;
		} else if(mean.equals(MeanType.GEOMETRIC)){
			value = geometricVelocity;
		} else if(mean.equals(MeanType.HARMONIC)){
			value = harmonicVelocity;
		}
		return value;
	}
	
	public BigDecimal getMeanStdDevDurationPerTransaction(MeanType mean){
		BigDecimal value = null;
		if(mean.equals(MeanType.ARITHMETIC)){
			value = arithmeticVelocityStdDev;
		} else if(mean.equals(MeanType.GEOMETRIC)){
			value = geometricVelocityStdDev;
		} else if(mean.equals(MeanType.HARMONIC)){
			throw new UnsupportedOperationException("Standard Deviation for Harmonic mean not available.");
		}
		return value;
	}
	
	/*Setter/Getter*/
    public BigDecimal getTransactionsPerSecond() {
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
