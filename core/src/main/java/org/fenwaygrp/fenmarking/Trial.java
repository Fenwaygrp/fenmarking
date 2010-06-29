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
