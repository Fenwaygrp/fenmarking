package org.fenwaygrp.fenmarking;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MetricResultUnitTest {

    private MetricResult metricResult;
    
    @Before
    public void before(){
        metricResult = new MetricResult(Arrays.asList(0L,25L,50L,75L,100L),1000L);
    }
    
    @Test
    public void testArithmeticVelocityIsNotNull() throws Exception {
        assertThat(metricResult.getMeanDurationPerTransaction(MeanType.ARITHMETIC), notNullValue());
    }
    
    @Test
    public void testGeometricVelocityIsNotNull() throws Exception {
        assertThat(metricResult.getMeanDurationPerTransaction(MeanType.GEOMETRIC), notNullValue());
    }
    
    @Test
    public void testHarmonicVelocityIsNotNull() throws Exception {
        assertThat(metricResult.getMeanDurationPerTransaction(MeanType.HARMONIC), notNullValue());
    }
    
    @Test
    public void testArithmeticVelocityStdDevIsNotNull() throws Exception {
        assertThat(metricResult.getMeanStdDevDurationPerTransaction(MeanType.ARITHMETIC), notNullValue());
    }
    
    @Test
    public void testGeometricVelocityStdDevIsNotNull() throws Exception {
        assertThat(metricResult.getMeanStdDevDurationPerTransaction(MeanType.GEOMETRIC), notNullValue());
    }


    @Test(expected=UnsupportedOperationException.class)
    public void testHarmonicVelocityStdDevThrowsException() throws Exception {
        metricResult.getMeanStdDevDurationPerTransaction(MeanType.HARMONIC);
    }
    
    @Test
    public void testModeListIsNotNull() throws Exception {
        assertThat(metricResult.getModes(), notNullValue());
    }

    @Test
    public void testMedianIsNotNull() throws Exception {
        assertThat(metricResult.getMedian(), notNullValue());
    }
    
    @Test
    public void testHighestValue() throws Exception {
        assertThat(metricResult.getHighestValue(), is(new BigDecimal("100.00")));
    }

    public void testThroughPut() throws Exception {
        assertThat(metricResult.getTransactionsPerSecond(), is(new BigDecimal("25")));
    }
    
}
