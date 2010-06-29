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
package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fenwaygrp.fenmarking.Algorithm;
import org.fenwaygrp.fenmarking.Fenmarking;
import org.fenwaygrp.fenmarking.FenmarkingDefaultImpl;
import org.fenwaygrp.fenmarking.MeanType;
import org.fenwaygrp.fenmarking.MetricResult;
import org.fenwaygrp.fenmarking.PerformanceConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;

public class WhenSubmittingWithListOfAlgorithmClassesAndCustomPerfConfig {

    private static Fenmarking fenmarking = new FenmarkingDefaultImpl();
    private static List<MetricResult> results;

    @BeforeClass
    public static void before() {
        AlgorithmOne.reset();
        AlgorithmTwo.reset();
        
        results = fenmarking.submit(new PerformanceConfiguration(1, 200, 1), Arrays.asList(
                AlgorithmOne.class, AlgorithmTwo.class));
    }

    /*Assertions for Algorithm One*/
    @Test
    public void shouldRunConfiguredExecutionsForAlgorithmOne() throws Exception {
        assertThat(AlgorithmOne.executions.get(), is(200));
    }

    @Test
    public void shouldRunConfiguredWarmUpsForAlgorithmOne() throws Exception {
        assertThat(AlgorithmOne.executions.get(), is(200));
    }

    @Test
    public void shouldHaveCalledSetUpForAlgorithmOne() throws Exception {
        assertThat(AlgorithmOne.isSetUpCalled, is(true));
    }
    
    @Test
    public void shouldHaveCalledTearDownForAlgorithmOne() throws Exception {
        assertThat(AlgorithmOne.isTearDownCalled, is(true));
    }
    
    @Test
    public void shouldHaveValidMetricResultsForAlgorithmOne() throws Exception {
        new MetricResultAssertions(results.get(0));
    }
    

    /*Assertions for Algorithm Two*/
    @Test
    public void shouldRunConfiguredExecutionsForAlgorithmTwo() throws Exception {
        assertThat(AlgorithmTwo.executions.get(), is(200));
    }

    @Test
    public void shouldRunConfiguredWarmUpsForAlgorithmTwo() throws Exception {
        assertThat(AlgorithmTwo.executions.get(), is(200));
    }

    @Test
    public void shouldHaveCalledSetUpForAlgorithmTwo() throws Exception {
        assertThat(AlgorithmTwo.isSetUpCalled, is(true));
    }
    
    @Test
    public void shouldHaveCalledTearDownForAlgorithmTwo() throws Exception {
        assertThat(AlgorithmTwo.isTearDownCalled, is(true));
    }
    
    @Test
    public void shouldHaveValidMetricResultsForAlgorithmTwo() throws Exception {
        new MetricResultAssertions(results.get(1));
    }
    
    @Test
    public void shouldAssertAlgorithmOneIsFasterThanAlgorithmTwo() throws Exception {
        assertThat(results.get(0).getMeanDurationPerTransaction(MeanType.ARITHMETIC), is(lessThan(results.get(1).getMeanDurationPerTransaction(MeanType.ARITHMETIC))));
    }

    @Test(expected=AssertionError.class)
    public void shouldThrowAssertionErrorWithEmptyAlgoList() throws Exception {
        List<Class<? extends Algorithm>> list = new ArrayList<Class<? extends Algorithm>>();
        fenmarking.submit(new PerformanceConfiguration(), list);
    }
    
}
