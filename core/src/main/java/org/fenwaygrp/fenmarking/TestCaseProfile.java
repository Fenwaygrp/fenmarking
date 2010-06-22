package org.fenwaygrp.fenmarking;

public class TestCaseProfile {

    private String name;
    private MetricResult metricResult;
    
    
    public TestCaseProfile(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public MetricResult getMetricResult() {
        return metricResult;
    }
    public void setMetricResult(MetricResult metricResult) {
        this.metricResult = metricResult;
    }
    
}
