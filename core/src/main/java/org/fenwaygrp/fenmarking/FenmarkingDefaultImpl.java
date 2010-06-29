package org.fenwaygrp.fenmarking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.hamcrest.MatcherAssert;

public class FenmarkingDefaultImpl implements Fenmarking {

    @SuppressWarnings("unchecked")
    public List<MetricResult> submit(PerformanceConfiguration configuration,
            List<Class<? extends Algorithm>> clazzez) {
        MatcherAssert.assertThat(clazzez.size(), is(greaterThan(0)));
        List<MetricResult> results = new ArrayList<MetricResult>();
        for (Class<? extends Algorithm> clazz : clazzez) {
            results.add(submitInternal(configuration, clazz, new HashMap()));
        }
        return results;
    }

    public MetricResult submit(Class<? extends Algorithm> clazz) {
        assertThat("Alogirhm is required", clazz, notNullValue());
        return submit(new PerformanceConfiguration(), clazz);
    }

    @SuppressWarnings("unchecked")
    public MetricResult submit(PerformanceConfiguration perfConfig, Class<? extends Algorithm> clazz) {
        assertThat("Alogirhm is required", clazz, notNullValue());
        return submitInternal(perfConfig, clazz, new HashMap());
    }

    @SuppressWarnings("unchecked")
    public List<MetricResult> submit(ScalabilityConfiguration configuration,
            Class<? extends Algorithm> clazz) {
        List<MetricResult> results = new ArrayList<MetricResult>();
        PerformanceConfiguration perfConfig = new PerformanceConfiguration(configuration);
        results.add(submitInternal(perfConfig, clazz, new HashMap()));
        for (int i = configuration.getIncrementBy(); i <= configuration.getMaxThreads(); i = i
                + configuration.getIncrementBy()) {
            perfConfig.setNumberOfThreads(i);
            results.add(submitInternal(perfConfig, clazz, new HashMap()));
        }
        return results;
    }

    public ScalabilityProfile submit(ScalabilityTrial scalabilityTrial) {

        List<Integer> threadCounts = new ArrayList<Integer>();
        threadCounts.add(1);
        for (int i = scalabilityTrial.getConfiguration().getIncrementBy(); i <= scalabilityTrial
                .getConfiguration().getMaxThreads(); i = i
                + scalabilityTrial.getConfiguration().getIncrementBy()) {
            threadCounts.add(i);
        }

        PerformanceTrial perfTrial = new PerformanceTrial();
        perfTrial.setAlgorithms(scalabilityTrial.getAlgorithms());
        perfTrial.setTestCases(scalabilityTrial.getTestCases());
        perfTrial.getConfiguration().setNumberOfWarmUps(
                scalabilityTrial.getConfiguration().getNumberOfWarmUps());
        perfTrial.getConfiguration().setNumberOfExecutions(
                scalabilityTrial.getConfiguration().getNumberOfExecutions());

        ScalabilityProfile scalabilityProfile = new ScalabilityProfile();
        for (Integer threadCount : threadCounts) {
            perfTrial.getConfiguration().setNumberOfThreads(threadCount);
            PerformanceProfile perfProfile = submitInternal(perfTrial);
            scalabilityProfile.addPerformanceProfile(perfProfile);
        }
        return scalabilityProfile;
    }

    @SuppressWarnings("unchecked")
    private MetricResult submitInternal(PerformanceConfiguration configuration,
            Class<? extends Algorithm> clazz, Map testData) {
        if (testData == null) {
            testData = new HashMap();
        }

        // Execute the warmup in threads
        // ExecutorService pool =
        // Executors.newFixedThreadPool(configuration.getNumberOfThreads());
        // for (int i = 0; i < configuration.getNumberOfWarmUps(); i++) {
        // pool.submit(new WarmUpRunnable(clazz, testData));
        // }
        // pool.shutdown();
        // try {
        // pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        // } catch (InterruptedException e) {
        // throw new RuntimeException(e);
        // }

        executeWarmUps(configuration, clazz, testData);

        // Execute the executions in threads
        List<Long> times = Collections.synchronizedList(new ArrayList<Long>());
        // List<Future> futures = new ArrayList<Future>();
        // pool =
        // Executors.newFixedThreadPool(configuration.getNumberOfThreads());
        Long start = System.currentTimeMillis();
        // for (int i = 0; i < configuration.getNumberOfExecutions(); i++) {
        // futures.add(pool.submit(new ExecutionRunnable(times, clazz,
        // testData)));
        // }
        //        
        // Iterator<Future> iterator = futures.iterator();
        // while(iterator.hasNext()){
        // Future future = iterator.next();
        // if(future.isDone()){
        // iterator.remove();
        // } else {
        // try {
        // future.get(100, TimeUnit.MILLISECONDS);
        // } catch (Exception e) {
        // e.printStackTrace();
        // throw new RuntimeException();
        // }
        // }
        // }
        //        
        // pool.shutdown();
        // try {
        // pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        // } catch (InterruptedException e) {
        // throw new RuntimeException(e);
        // }

        executeExecutions(configuration, clazz, testData, times);
        Long end = System.currentTimeMillis();

        MetricResult result = new MetricResult(times, (end - start));
        return result;
    }

    private void executeWarmUps(PerformanceConfiguration configuration,
            Class<? extends Algorithm> clazz, Map testData) {
        executeInThreads(false, configuration.getNumberOfThreads(), configuration
                .getNumberOfWarmUps(), clazz, testData, null);
    }

    private void executeExecutions(PerformanceConfiguration configuration,
            Class<? extends Algorithm> clazz, Map testData, List<Long> times) {
        executeInThreads(true, configuration.getNumberOfThreads(), configuration
                .getNumberOfExecutions(), clazz, testData, times);
    }

    private void executeInThreads(Boolean isExecution, Integer numberOfThreads,
            Integer numberOfIterations, Class<? extends Algorithm> clazz, Map testData,
            List<Long> times) {

        List<Future> futures = new ArrayList<Future>();
        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            if (isExecution) {
                futures.add(pool.submit(new ExecutionRunnable(times, clazz, testData)));
            } else {
                futures.add(pool.submit(new WarmUpRunnable(clazz, testData)));
            }
        }
        
        assertThat(futures.size(), is(numberOfIterations));
        
        Iterator<Future> iterator = futures.iterator();
        while (iterator.hasNext()) {
            Future future = iterator.next();
            if (future.isDone()) {
                iterator.remove();
            } else {
                try {
                    future.get(100, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        }

        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @SuppressWarnings("unchecked")
    private PerformanceProfile submitInternal(PerformanceTrial trial) {
        PerformanceProfile perfProfile = new PerformanceProfile();

        for (Map.Entry<String, Class<? extends Algorithm>> algorithmEntry : trial.getAlgorithms()
                .entrySet()) {
            AlgorithmProfile algorithmProfile = new AlgorithmProfile(algorithmEntry.getKey());
            perfProfile.addAlgorithmProfile(algorithmProfile);
            for (Map.Entry<String, Map> testDataEntry : trial.getTestCases().entrySet()) {
                PerformanceConfiguration config = new PerformanceConfiguration(trial
                        .getConfiguration());
                MetricResult metricResult = submitInternal(config, algorithmEntry.getValue(),
                        testDataEntry.getValue());
                TestCaseProfile testCaseProfile = new TestCaseProfile(testDataEntry.getKey());
                testCaseProfile.setMetricResult(metricResult);
                algorithmProfile.addTestCaseProfile(testCaseProfile);
            }
        }
        return perfProfile;
    }

}
