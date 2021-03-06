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

import static org.hamcrest.Matchers.notNullValue;

import org.hamcrest.MatcherAssert;

/**
 * The PerformanceConfiguration additionally allows the user to configure
 * how many concurrent thread to use to execute the algorithm.  
 * @author Saad Khawaja
 *
 */
public class PerformanceConfiguration extends Configuration {

    private Integer numberOfThreads = 1;

    public PerformanceConfiguration() {
    }

    
    
    /**
     * This constructor only copies the properties in the base configuration
     * @param configuration
     */
    public PerformanceConfiguration(Configuration configuration) {
        super(configuration.getNumberOfWarmUps(), configuration.getNumberOfExecutions());
    }

    public PerformanceConfiguration(Integer numberOfWarmUp, Integer numberOfExecutions,
            Integer numberOfThreads) {
        super(numberOfWarmUp, numberOfExecutions);
        MatcherAssert.assertThat(numberOfThreads, notNullValue());
        this.numberOfThreads = numberOfThreads;
    }

    public PerformanceConfiguration(Integer numberOfThreads) {
        super();
        MatcherAssert.assertThat(numberOfThreads, notNullValue());
        this.numberOfThreads = numberOfThreads;
    }

    
    public Integer getNumberOfThreads() {
        return numberOfThreads;
    }

    /**
     * This allows the user to set the number of concurrent thread to use to execute the
     * algorithm under tests.
     * @param numberOfThreads
     */
    protected void setNumberOfThreads(Integer numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }
    
}
