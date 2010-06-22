package org.fenwaygrp.fenmarking;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticCalculatorWithDiffDataPointsUnitTest {

    private StatisticCalculator statisticCalculator = new StatisticCalculatorDefaultImpl();

    private List<Long> data = new ArrayList<Long>();

    @Before
    public void before() {
        data.add(0L);
        data.add(12L);
        data.add(25L);
        data.add(37L);
        data.add(50L);
        data.add(63L);
        data.add(75L);
        data.add(87L);
        data.add(100L);
    }

    @Test
    public void testArithmeticMean() throws Exception {
        BigDecimal result = statisticCalculator.getArithmeticMean(data);
        assertThat(result, is(new BigDecimal("49.89")));
    }

    @Test
    public void testArithmeticStdDev() throws Exception {
        BigDecimal result = statisticCalculator.getArithmeticMeanStdDev(data);
        assertThat(result, is(new BigDecimal("32.32")));
    }

    @Test
    public void testGeometricMean() throws Exception {
        BigDecimal result = statisticCalculator.getGeometricMean(data);
        assertThat(result, is(new BigDecimal("30.50")));
    }

    @Test
    public void testGeometricStdDevOf() throws Exception {
        BigDecimal result = statisticCalculator.getGeometricMeanStdDev(data);
        assertThat(result, is(new BigDecimal("3.91")));
    }

    @Test
    public void testHarmonicMeanOf() throws Exception {
        assertThat(statisticCalculator.getHarmonicMean(data), is(new BigDecimal("7.37")));
    }

    @Test
    public void testRelationshipOfMeans() throws Exception {
        assertThat(statisticCalculator.getArithmeticMean(data), greaterThan(statisticCalculator
                .getGeometricMean(data)));
        assertThat(statisticCalculator.getGeometricMean(data), greaterThan(statisticCalculator
                .getHarmonicMean(data)));
    }

    @Test
    public void testMedian() throws Exception {
        assertThat(statisticCalculator.getMedian(data), is(new BigDecimal("50.00")));
    }

    @Test
    public void testMedianWithEvenNumberOfValuesInList() throws Exception {
        data.clear();
        data.add(0L);
        data.add(25L);
        data.add(75L);
        data.add(100L);
        assertThat(statisticCalculator.getMedian(data), is(new BigDecimal("50.00")));
    }

    @Test
    public void testModeWithUniModalSet() throws Exception {
        data.add(100L);
        List<Long> modes = statisticCalculator.getMode(data);
        assertThat(modes.size(), is(1));
        assertThat(modes.get(0), is(100L));
    }

    @Test
    public void testModeWithBiModalSet() throws Exception {
        data.add(100L);
        data.add(75L);

        List<Long> modes = statisticCalculator.getMode(data);
        assertThat(modes.size(), is(2));
        assertThat(modes.contains(75L), is(true));
        assertThat(modes.contains(100L), is(true));
    }

}
