/*
   Copyright 2010 fenwaygrp.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.fenwaygrp.fenmarking;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * This class contains the results and statistical data computed from the
 * timing data collected.  The client can not create a instance of this class.
 * 
 * @author Saad Khawaja
 *
 */
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
    private BigDecimal minValue;
    
	
    private StatisticCalculator calculator = new StatisticCalculatorDefaultImpl();
	
	protected MetricResult(List<Long> values, Long totalExecutionTime) {
		arithmeticVelocity = calculator.getArithmeticMean(values);
		geometricVelocity = calculator.getGeometricMean(values);
		harmonicVelocity = calculator.getHarmonicMean(values);
		arithmeticVelocityStdDev = calculator.getArithmeticMeanStdDev(values);
        geometricVelocityStdDev = calculator.getGeometricMeanStdDev(values);
		modes = calculator.getMode(values);
		median = calculator.getMedian(values);
        
        throughput = calculateTps(totalExecutionTime, values.size());
        highestValue = calculateHighestValue(values);
        minValue = calculateMinValue(values);
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
	
	private BigDecimal calculateMinValue(List<Long> values){
        Long minValue = 0L;
        for(Long item : values){
            if(item < minValue){
                minValue = item;
            }
        }
        return new FenmarkBigDecimal(minValue).toBigDecimal(2, RoundingMode.HALF_EVEN);
	    
	}
	
	/**
	 * This method returns the mean/average duration per transaction. Duration is
	 * in milliseconds.  Transaction is defined as the amount of work done in 
	 * Algorithm.execution() method. 
	 * 
	 * @param mean specify the enum type for which you want the mean computation
	 * @return big decimal representation of the computation.
	 */
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
	
	/**
	 * This method returns the standard deviation for the specified mean. Currently
	 * the standard deviation for harmonic mean is not available.
	 *  
	 * @param mean specify the enum type for which you want the standard
	 * deviation.
	 * @return  big decimal representation of the standard deviation
	 */
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
	
    /**
     * This method returns the number of transactions processed in one second.
     * Transaction is defined as the amount of work done in Algorithm.execution()
     * method.
     * 
     * @return big decimal representation for the value.
     */
	public BigDecimal getTransactionsPerSecond() {
        return throughput;
    }

    public BigDecimal getMedian() {
        return median;
    }

    public List<Long> getModes() {
        return modes;
    }

    public BigDecimal getMaxValue() {
        return highestValue;
    }
    
    public StatisticCalculator getCalculator() {
        return calculator;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

}
