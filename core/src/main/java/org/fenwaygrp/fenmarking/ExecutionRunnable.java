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
import java.util.Map;

/**
 * This is a non public class for internal user. This class is used by the 
 * framework to perform the actual execution in a thread and collect times
 * around the execution.
 * @author Saad Khawaja
 *
 */
class ExecutionRunnable extends AbstractRunnable {

    private List<Long> times;
	
	public ExecutionRunnable(List<Long> times, Class<? extends Algorithm> clazz, Map params) {
	    super(clazz, params);
	    this.times = times;
	}
  
	public void runInternal(Algorithm instance) {
		long start = System.currentTimeMillis();
		instance.execution();
		long stop = System.currentTimeMillis();
		times.add(stop-start);
	}
	
}
