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

import java.util.List;

/**
 * This is the main interface that the client will use to invoke the micro benchmark 
 * framework. 
 * @author Saad Khawaja
 * 
 */
public interface Fenmarking {

    /**
     * This method allows you to specific a single algorithm to benchmark. 
     * The framework uses the default configuration.
     * @param clazz The class which encapsulates the algorithm under test. 
     * @return the results of the benchmarking.
     */
    MetricResult submit(Class<? extends Algorithm> clazz);

    /**
     * This method allows you to specific a single algorithm to benchmark and additionally
     * supply a custom configuration to apply.
     * @param configuration the configuration to apply to the benchmarking
     * @param clazz The class which encapsulates the algorithm under test. 
     * @return
     */
    MetricResult submit(PerformanceConfiguration configuration, Class<? extends Algorithm> clazz);

    /**
     * This method allows you to specify a single algorithm to benchmark and additionally 
     * supply a custom scalability configuration.
     * configuration.
     * @param configuration The configuration to apply to the benchmarking
     * @param clazz The class which encapsulates the algorithm under test.
     * @return
     */
    List<MetricResult> submit(ScalabilityConfiguration configuration, Class<? extends Algorithm> clazz);

    List<MetricResult> submit(PerformanceConfiguration configuration,
            List<Class<? extends Algorithm>> clazzez);

    ScalabilityProfile submit(ScalabilityTrial trial);

}
