package org.fenwaygrp.fenmarking;

import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.fenwaygrp.fenmarking.StatisticCalculatorDefaultImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticCalculatorWithSameDataPointsUnitTest {

	private StatisticCalculator statisticCalculator = new StatisticCalculatorDefaultImpl();
	
	private List<Long> data = new ArrayList<Long>();
	
	@Before
	public void before(){
		data.add(50L);
		data.add(50L);
		data.add(50L);
		data.add(50L);
	}
	
	@Test
	public void shouldHaveArithmeticMeanOf50() throws Exception {
		BigDecimal result = statisticCalculator.getArithmeticMean(data);
		assertThat(result, is(new BigDecimal("50.00")));
	}

	@Test
	public void shouldHaveArithmeticStdDevOf() throws Exception {
		BigDecimal result = statisticCalculator.getArithmeticMeanStdDev(data);
		assertThat(result, is(new BigDecimal("0.00")));
	}

	@Test
	public void shouldHaveGeometricMeanOf() throws Exception {
		BigDecimal result = statisticCalculator.getGeometricMean(data);
		assertThat(result, is(new BigDecimal("50.00")));
	}
	
	@Test
	public void shouldHaveGeometricStdDevOf() throws Exception {
	    assertThat(statisticCalculator.getGeometricMeanStdDev(data), is(new BigDecimal("1.00")));
	}
	
	
	@Test
	public void shouldHaveHarmonicMeanOf() throws Exception {
	    assertThat(statisticCalculator.getHarmonicMean(data), is(new BigDecimal("50.00")));
	}

    @Test
    public void testRelationshipOfMeans() throws Exception {
        assertThat(statisticCalculator.getArithmeticMean(data), is(statisticCalculator
                .getGeometricMean(data)));
        assertThat(statisticCalculator.getGeometricMean(data), is(statisticCalculator
                .getHarmonicMean(data)));
    }
	
	@Test
	public void shouldHaveMedianOf() throws Exception {
		assertThat(statisticCalculator.getMedian(data), is(new BigDecimal("50.00")));
	}
	
	@Test
	public void shouldHaveModeOf() throws Exception {
		List<Long> mode = statisticCalculator.getMode(data);
		assertThat(mode.size(), is(1));
		assertThat(mode.get(0), is(50L));
	}

	
}
