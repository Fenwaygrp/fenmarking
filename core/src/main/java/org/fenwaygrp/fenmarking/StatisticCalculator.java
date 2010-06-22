package org.fenwaygrp.fenmarking;

import java.math.BigDecimal;
import java.util.List;

interface StatisticCalculator {

	BigDecimal getArithmeticMean(List<Long> values);

	BigDecimal getArithmeticMeanStdDev(List<Long> values);

	BigDecimal getGeometricMean(List<Long> values);

	BigDecimal getGeometricMeanStdDev(List<Long> values);

	BigDecimal getHarmonicMean(List<Long> values);

	BigDecimal getMedian(List<Long> values);

	List<Long> getMode(List<Long> values);

}