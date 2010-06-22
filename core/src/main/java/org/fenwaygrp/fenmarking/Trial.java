package org.fenwaygrp.fenmarking;

import java.util.LinkedHashMap;
import java.util.Map;

public class Trial {

    private Map<String, Class<? extends Algorithm>> algorithms = new LinkedHashMap<String, Class<? extends Algorithm>>();    
    private Map<String, Map> testcases = new LinkedHashMap<String, Map>();

    public void addAlgorithm(String name, Class<? extends Algorithm> clazz){
        algorithms.put(name, clazz);
    }
    
    public void addTestCase(String name, Map testData){
        testcases.put(name, testData);
    }
    
    Map<String, Class<? extends Algorithm>> getAlgorithms(){     
        return algorithms;
    }
    
    Map<String, Map> getTestCases(){ 
        return testcases;
    }
    
    void setAlgorithms(Map<String, Class<? extends Algorithm>> algorithms){
        this.algorithms = algorithms;
    }
    
    void setTestCases(Map<String, Map> testcases){
        this.testcases = testcases;
    }
    
}
