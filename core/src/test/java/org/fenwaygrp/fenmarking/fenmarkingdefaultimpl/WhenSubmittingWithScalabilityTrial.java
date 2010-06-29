package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.fenwaygrp.fenmarking.AlgorithmProfile;
import org.fenwaygrp.fenmarking.Fenmarking;
import org.fenwaygrp.fenmarking.FenmarkingDefaultImpl;
import org.fenwaygrp.fenmarking.MetricResult;
import org.fenwaygrp.fenmarking.PerformanceProfile;
import org.fenwaygrp.fenmarking.ScalabilityConfiguration;
import org.fenwaygrp.fenmarking.ScalabilityProfile;
import org.fenwaygrp.fenmarking.ScalabilityTrial;
import org.fenwaygrp.fenmarking.TestCaseProfile;
import org.junit.BeforeClass;
import org.junit.Test;

public class WhenSubmittingWithScalabilityTrial {

    private static Fenmarking fenmarking = new FenmarkingDefaultImpl();
    private static ScalabilityProfile scalabilityProfile;
    private static PerformanceProfile perfProfile;
    private static AlgorithmProfile algoProfile;
    private static TestCaseProfile testCaseProfile;
    private static MetricResult result;
    private static Map<String, String> testCaseOne = new HashMap<String, String>();
    private static ScalabilityTrial trial = new ScalabilityTrial();

    @BeforeClass
    public static void beforeClass() {
        AlgorithmOne.reset();
        AlgorithmTwo.reset();
        trial.setConfiguration(new ScalabilityConfiguration(10, 5, 100, 200));
        trial.addAlgorithm("algorithmOne", AlgorithmOne.class);
        trial.addAlgorithm("algorithmTwo", AlgorithmTwo.class);
        trial.addTestCase("TestCaseOne", testCaseOne);

        scalabilityProfile = fenmarking.submit(trial);
        perfProfile = scalabilityProfile.getPerformanceProfiles().get(0);
        algoProfile = perfProfile.getAlgorithmProfiles().get(0);
        testCaseProfile = algoProfile.getTestCaseProfiles().get(0);
        result = testCaseProfile.getMetricResult();
    }

    @Test
    public void shouldHaveThreePerformanceProfiles() throws Exception {
        assertThat(scalabilityProfile.getPerformanceProfiles().size(), is(3));
    }

    @Test
    public void shouldHaveTwoAlgoithmProfilesPerPerfProfile() throws Exception {
        assertThat(perfProfile.getAlgorithmProfiles().size(), is(2));
    }

    @Test
    public void shouldHaveOneTestCaseProfilePerAlgorithmProfile() throws Exception {
        assertThat(algoProfile.getTestCaseProfiles().size(), is(1));
    }

    @Test
    public void shouldHaveMetricResultPerTestCaseProfile() throws Exception {
        assertThat(testCaseProfile.getMetricResult(), notNullValue());
    }

    @Test
    public void shouldHaveValidMetricResults() throws Exception {
        new MetricResultAssertions(result);
    }

    @Test
    public void shouldHaveAlgorithmOneValueInTestCase() throws Exception {
        assertThat(testCaseOne.get("AlgorithmOne"), notNullValue());
    }

    @Test
    public void shouldHaveAlgorithmTwoValueInTestCase() throws Exception {
        assertThat(testCaseOne.get("AlgorithmTwo"), notNullValue());
    }

    @Test
    public void shouldHaveRunDefaultWarmsForAlgorithmOne() throws Exception {
        assertThat(AlgorithmOne.warmups.get(), is(trial.getConfiguration().getNumberOfWarmUps()
                * scalabilityProfile.getPerformanceProfiles().size()));
    }

    @Test
    public void shouldHaveRunDefaultExecutionsAlgorithmOne() throws Exception {
        assertThat(AlgorithmOne.executions.get(), is(trial.getConfiguration().getNumberOfExecutions()
                * scalabilityProfile.getPerformanceProfiles().size()));
    }

    @Test
    public void shouldHaveRunSetUpAlgorithmOne() throws Exception {
        assertThat(AlgorithmOne.isSetUpCalled, is(true));
    }

    @Test
    public void shouldHaveRunTearDownAlgorithmOne() throws Exception {
        assertThat(AlgorithmOne.isTearDownCalled, is(true));
    }
    
    @Test
    public void shouldHaveRunDefaultWarmsForAlgorithmTwo() throws Exception {
        assertThat(AlgorithmTwo.warmups.get(), is(trial.getConfiguration().getNumberOfWarmUps()
                * scalabilityProfile.getPerformanceProfiles().size()));
    }

    @Test
    public void shouldHaveRunDefaultExecutionsAlgorithmTwo() throws Exception {
        assertThat(AlgorithmTwo.executions.get(), is(trial.getConfiguration().getNumberOfExecutions()
                * scalabilityProfile.getPerformanceProfiles().size()));
    }

    @Test
    public void shouldHaveRunSetUpAlgorithmTwo() throws Exception {
        assertThat(AlgorithmTwo.isSetUpCalled, is(true));
    }

    @Test
    public void shouldHaveRunTearDownAlgorithmTwo() throws Exception {
        assertThat(AlgorithmTwo.isTearDownCalled, is(true));
    }
    
}
