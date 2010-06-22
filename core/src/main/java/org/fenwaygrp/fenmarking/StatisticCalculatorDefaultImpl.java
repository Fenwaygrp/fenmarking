package org.fenwaygrp.fenmarking;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StatisticCalculatorDefaultImpl implements StatisticCalculator {

    public BigDecimal getArithmeticMean(List<Long> values) {
        FenmarkBigDecimal arithmeticMean = new FenmarkBigDecimal();
        for (Long value : values) {
            arithmeticMean = arithmeticMean.add(value);
        }
        arithmeticMean = arithmeticMean.divide(values.size());
        return toBigDecimal(arithmeticMean);
    }

    public BigDecimal getArithmeticMeanStdDev(List<Long> values) {
        FenmarkBigDecimal mean = new FenmarkBigDecimal(getArithmeticMean(values));
        FenmarkBigDecimal stddev = new FenmarkBigDecimal();
        for (Long value : values) {
            FenmarkBigDecimal diff = new FenmarkBigDecimal(value).subtract(mean);
            FenmarkBigDecimal sqrd = new FenmarkBigDecimal(Math.pow(diff.doubleValue(), 2));
            stddev = stddev.add(sqrd);
        }
        stddev = stddev.divide(values.size());
        stddev = new FenmarkBigDecimal(Math.sqrt(stddev.doubleValue()));
        return toBigDecimal(stddev);
    }

    public BigDecimal getGeometricMean(List<Long> values) {
        FenmarkBigDecimal sumOfTheLogs = new FenmarkBigDecimal();
        for (Long value : values) {
            if (value == 0L) {
                value = 1L;
            }
            FenmarkBigDecimal log = new FenmarkBigDecimal(Math.log(value));
            sumOfTheLogs = sumOfTheLogs.add(log);
        }
        FenmarkBigDecimal averageOfTheLogs = sumOfTheLogs.divide(values.size());
        FenmarkBigDecimal expOfTheAverage = new FenmarkBigDecimal(Math.exp(averageOfTheLogs
                .doubleValue()));
        return toBigDecimal(expOfTheAverage);
    }

    public BigDecimal getGeometricMeanStdDev(List<Long> values) {
        BigDecimal geometricMean = getGeometricMean(values);
        FenmarkBigDecimal logOfGeoMean = new FenmarkBigDecimal(Math
                .log(geometricMean.doubleValue()));
        FenmarkBigDecimal sumOfTheSquareOfDiffWithGeoMean = new FenmarkBigDecimal();
        // First Compute the square of log diff.
        for (Long value : values) {
            if (value == 0) {
                value = 1L;
            }
            FenmarkBigDecimal logOfValue = new FenmarkBigDecimal(Math.log(value.doubleValue()));
            FenmarkBigDecimal diff = logOfValue.subtract(logOfGeoMean);
            diff = new FenmarkBigDecimal(Math.abs(diff.doubleValue()));
            FenmarkBigDecimal squareOfDiffWithGeoMean = new FenmarkBigDecimal(Math.pow(diff
                    .doubleValue(), 2d));
            sumOfTheSquareOfDiffWithGeoMean = sumOfTheSquareOfDiffWithGeoMean
                    .add(squareOfDiffWithGeoMean);
        }

        FenmarkBigDecimal sumOfTheSquareOfDiffWithGeoMeanDividedByN = sumOfTheSquareOfDiffWithGeoMean
                .divide(values.size());
        FenmarkBigDecimal sqrt = new FenmarkBigDecimal(Math
                .sqrt(sumOfTheSquareOfDiffWithGeoMeanDividedByN.doubleValue()));
        FenmarkBigDecimal stdDev = new FenmarkBigDecimal(Math.exp(sqrt.doubleValue()));
        return toBigDecimal(stdDev);
        
    }

    public BigDecimal getHarmonicMean(List<Long> values) {
        FenmarkBigDecimal result = new FenmarkBigDecimal(0);
        for(Long value : values){
            if(value == 0){
                value = 1L;
            }
            FenmarkBigDecimal reciprocal = new FenmarkBigDecimal(1).divide(value);
            result = result.add(reciprocal);
        }
        result = new FenmarkBigDecimal(values.size()).divide(result);
        return toBigDecimal(result);
    }

    public BigDecimal getMedian(List<Long> values) {
        List<Long> sorted = new ArrayList<Long>(values);
        Collections.sort(sorted);
        BigDecimal median = null;
        if ((sorted.size() % 2) == 0) {
            List<Long> tmp = new ArrayList<Long>();
            int index = sorted.size() / 2;
            tmp.add(sorted.get(index - 1));
            tmp.add(sorted.get(index));
            median = getArithmeticMean(tmp);
        } else {
            median = new BigDecimal(sorted.get(sorted.size() / 2));
        }
        return median.setScale(2, RoundingMode.HALF_EVEN);
    }

    public List<Long> getMode(List<Long> values) {
        // Create a map with key as the data point from the set and
        // value as the count of occurences of the data point
        int highestCount = 0;
        Map<Long, Integer> keyedByValue = new HashMap<Long, Integer>();
        for (Long value : values) {
            if (keyedByValue.containsKey(value)) {
                int count = keyedByValue.get(value);
                count++;
                if (highestCount < count) {
                    highestCount = count;
                }
                keyedByValue.put(value, count);
            } else {
                keyedByValue.put(value, 1);
            }
        }

        // Get the values
        List<Long> modes = new ArrayList<Long>();
        for (Map.Entry<Long, Integer> entry : keyedByValue.entrySet()) {
            if (entry.getValue() == highestCount) {
                modes.add(entry.getKey());
            }

        }
        return modes;
    }

    private BigDecimal toBigDecimal(FenmarkBigDecimal fenmarkBigDecimal){
        return fenmarkBigDecimal.toBigDecimal(2, RoundingMode.HALF_EVEN);
    }
    
}
