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

public class ScalabilityConfiguration extends Configuration {

    private Integer maxThreads=1;
    private Integer incrementBy=1;
    
    public ScalabilityConfiguration() {
    }
    
    public ScalabilityConfiguration(Integer maxThreads, Integer incrementBy, Integer numberOfWarmUps, Integer numberOfExecutions) {
        super(numberOfWarmUps, numberOfExecutions);
        this.maxThreads = maxThreads;
        this.incrementBy = incrementBy;
    }
    
    public ScalabilityConfiguration(Integer maxThreads, Integer incrementBy) {
        this.maxThreads = maxThreads;
        this.incrementBy = incrementBy;
    }
    
    public Integer getMaxThreads() {
        return maxThreads;
    }
    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }
    public Integer getIncrementBy() {
        return incrementBy;
    }
    public void setIncrementBy(Integer incrementBy) {
        this.incrementBy = incrementBy;
    }
    

    
    
}
