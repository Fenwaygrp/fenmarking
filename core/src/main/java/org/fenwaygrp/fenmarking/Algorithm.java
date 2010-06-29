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

import java.util.Map;

/**
 * This interface provides the contract to allow clients to code/encapsulate their algorithm
 * which they desire to mirco benchmark.  The framework has two phases the warmUp and 
 * execution, each phase has the following life-cycle respectively. 
 * WarmUp phase {setUp, warmUp, tearDown}. Execution Phase {setUp, execution, tearDown}.  
 * The number of times the warmup and execution phases are run is based on the 
 * parameters defined in the Configuration class.
 * Note the execution loop does not clock the time for setUp and tearDown as expected.
 *  
 * @author Saad Khawaja
 *
 */
public interface Algorithm {

    /**
     * This method is the first method that the framework calls as part of the life cycle 
     * operations.  This method allows the client to perform any setup of the environment 
     * that maybe necessary for the warmUp or actual execution of the code. Do not put any code in
     * here that you wish to micro benchmark.  As expected this method is not part of the 
     * micro benchmark timing.
     * @param params This is a map that you pass to one of the submit() operations of the
     * Fenmarking interface. Use the map to pass in the necessary parameter to setup the 
     * algorithm.
     */
    @SuppressWarnings("unchecked")
	void setUp(Map params);
	
    /**
     * This method is the second method that the framework calls as part of the life cycle
     * during the warmUp phase. Typically this method will be very similar to the execution
     * operation. As expected this method is not part of the 
     * micro benchmark timing.
     */
	void warmUp();
	
	/**
	 * This method is the second method that the framework calls as part of the life cycle
	 * during the execution phase. This method should contain only the code that needs
	 * to be micro benchmarked.
	 */
	void execution();
	
	/**
	 * This method is the third and last method that the framework calls as part of the life
	 * cycle phases.  Use this method to clean up any resource that may have been modified 
	 * by the warmUp or execution phase.  Use this method to put the resources in a state so
	 * that the setUp method may properly initialize for the next run. As expected this method is not part of the 
     * micro benchmark timing.
	 */
	void tearDown();
	
}
