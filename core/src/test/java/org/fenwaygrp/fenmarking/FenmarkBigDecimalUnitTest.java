package org.fenwaygrp.fenmarking;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.After;
import org.junit.Test;

public class FenmarkBigDecimalUnitTest {

    private BigDecimal result;
    
    private void assertScale(){
        assertThat(result.scale(), is(6));
    }
    
	@Test
	public void testCreatingWithNoArgConstructor() throws Exception {
		result = new FenmarkBigDecimal().toBigDecimal();
		assertThat(result, is(new BigDecimal("0.000000")));
		assertScale();
	}
	
	@Test
	public void testCreatingWithLong() throws Exception {
		result = new FenmarkBigDecimal(123.1234).toBigDecimal();
		assertThat(result, is(new BigDecimal("123.123400")));
		assertScale();
	}
	
	@Test
	public void testCreatingWithInteger() throws Exception {
		result = new FenmarkBigDecimal(123).toBigDecimal();
		assertThat(result, is(new BigDecimal("123.000000")));
		assertScale();
	}
	
	@Test
	public void testCreatingWithDouble() throws Exception {
		result = new FenmarkBigDecimal(1.36).toBigDecimal();
		assertThat(result, is(new BigDecimal("1.360000")));
		assertScale();
	}

	@Test
	public void testCreatingWithBigDecimal() throws Exception {
		result = new FenmarkBigDecimal(new BigDecimal("1.36")).toBigDecimal();
		assertThat(result, is(new BigDecimal("1.360000")));
		assertScale();
	}
	
	@Test
	public void testCreatingWithString() throws Exception {
		result = new FenmarkBigDecimal("1.36").toBigDecimal();
		assertThat(result, is(new BigDecimal("1.360000")));
		assertScale();
	}
	
	@Test
	public void testAddOperationWithFenmarkBigDecimal() throws Exception {
		result = new FenmarkBigDecimal(1.99d).add(new FenmarkBigDecimal(2.99d)).toBigDecimal();
		assertThat(result, is(new BigDecimal("4.980000")));
		assertScale();
	}
	
	@Test
	public void testAddOperationWithLong() throws Exception {
		result = new FenmarkBigDecimal(1.99d).add(2l).toBigDecimal();
		assertThat(result, is(new BigDecimal("3.990000")));
		assertScale();
	}
	
	@Test
	public void testDivideOperationWithLong() throws Exception {
		result = new FenmarkBigDecimal(5.5d).divide(new FenmarkBigDecimal(2)).toBigDecimal();
		assertThat(result, is(new BigDecimal("2.750000")));
		assertScale();
	}
	
	@Test
	public void testSubtractOperationWithFenmarkBigDecimal() throws Exception {
		result = new FenmarkBigDecimal(5.5d).subtract(new FenmarkBigDecimal(2.5d)).toBigDecimal();
		assertThat(result, is(new BigDecimal("3.000000")));
		assertScale();
	}
	
	@Test
    public void toBigDecimalWithScaleAndRoundingMode() throws Exception {
        FenmarkBigDecimal value = new FenmarkBigDecimal("49.9980");
        assertThat(value.toBigDecimal(2,RoundingMode.HALF_EVEN), is(new BigDecimal("50.00")));
    }
	
}
