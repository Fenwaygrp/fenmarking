package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.fenwaygrp.fenmarking.MetricResult;
import org.junit.Test;

public class MetricResultAssertions {

    private MetricResult result;
    
    public MetricResultAssertions(MetricResult result) throws Exception {
        this.result = result;
        shouldHaveNotNullArithmeticStdDevValue();
        shouldHaveNotNullArithmeticValue();
        shouldHaveNotNullGeometricStdDev();
        shouldHaveNotNullGeometricValue();
        shouldHaveNotNullHarmonic();
        shouldHaveNotNullHighestValue();
        shouldHaveNotNullMedian();
        shouldHaveNotNullMode();
        shouldHaveNotNullThroughput();
    }
    
    @Test
    public void shouldHaveNotNullArithmeticValue() throws Exception {
        assertThat(result.getArithmeticVelocity(), notNullValue());
    }

    @Test
    public void shouldHaveNotNullArithmeticStdDevValue() throws Exception {
        assertThat(result.getArithmeticVelocityStdDev(), notNullValue());
    }

    @Test
    public void shouldHaveNotNullGeometricValue() throws Exception {
        assertThat(result.getGeometricVelocity(), notNullValue());
    }
 
    @Test
    public void shouldHaveNotNullGeometricStdDev() throws Exception {
        assertThat(result.getGeometricVelocityStdDev(), notNullValue());
    }
    
    @Test
    public void shouldHaveNotNullHarmonic() throws Exception {
        assertThat(result.getHarmonicVelocity(), notNullValue());
    }
    
    @Test
    public void shouldHaveNotNullHighestValue() throws Exception {
        assertThat(result.getHighestValue(), notNullValue());
    }
    
    @Test
    public void shouldHaveNotNullMedian() throws Exception {
        assertThat(result.getMedian(), notNullValue());
    }
    
    @Test
    public void shouldHaveNotNullMode() throws Exception {
        assertThat(result.getModes(), notNullValue());
        assertThat(result.getModes().size(), is(greaterThan(0)));
    }
    
    @Test
    public void shouldHaveNotNullThroughput() throws Exception {
        assertThat(result.getThroughput(), notNullValue());
    }
    
    
}


