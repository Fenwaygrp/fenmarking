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
     * Use this method to pass a instance of the algorithm that you want to micro benchmark.
     * The instance will be reused and the Default PerformanceConfiguration will be applied.
     * @param algorithm the algorithm to benchmark
     * @return the results of the benchmarking.
     */
    MetricResult submit(Class<? extends Algorithm> clazz);

    /**
     * Use this method to pass an instance of the algorithm you want to micro benchmark and
     * a custom configuration to apply.
     * @param configuration
     * @param algorithm
     * @return
     */
    MetricResult submit(PerformanceConfiguration configuration, Class<? extends Algorithm> clazz);

    /**
     * This method allows you to pass a algorithm instance and a custom scalability
     * configuration.
     * @param configuration
     * @param algorithm
     * @return
     */
    List<MetricResult> submit(ScalabilityConfiguration configuration, Class<? extends Algorithm> clazz);

    /**
     * 
     * @param configuration
     * @param clazzez
     * @return
     */
    List<MetricResult> submit(PerformanceConfiguration configuration,
            List<Class<? extends Algorithm>> clazzez);

    ScalabilityProfile submit(ScalabilityTrial trial);

}
