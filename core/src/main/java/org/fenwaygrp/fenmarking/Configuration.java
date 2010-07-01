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

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.hamcrest.MatcherAssert;

/**
 * This is the base configuration and encapsulate certain properties that are common to the 
 * different available configuration.
 * @author Saad Khawaja
 *
 */
public abstract class Configuration {

    public static final Integer defaultWarmUpCount=2000;
    public static final Integer defaultExecutionCount=1000;
    
	private Integer numberOfWarmUps = defaultWarmUpCount;
	private Integer numberOfExecutions = defaultExecutionCount;

	public Configuration() {
    }
	
	/**
	 * 
	 * @param numberOfWarmUps  This parameters allows you to configure the number of times
	 * you want to execute the warm up phase.
	 * @param numberOfExecutions This parameter allows you to configure the number of times
	 * you want to execute the execution phase.
	 */
	public Configuration(Integer numberOfWarmUps, Integer numberOfExecutions) {
	    MatcherAssert.assertThat(numberOfWarmUps,notNullValue());
	    MatcherAssert.assertThat(numberOfExecutions,notNullValue());
	    MatcherAssert.assertThat(numberOfWarmUps, is(greaterThan(0)));
	    MatcherAssert.assertThat(numberOfExecutions, is(greaterThan(0)));
		this.numberOfWarmUps = numberOfWarmUps;
		this.numberOfExecutions = numberOfExecutions;
	}

	public Integer getNumberOfWarmUps() {
		return numberOfWarmUps;
	}

	/**
	 * This is the number of times the micro benchmark framework will 
	 * iterate over the warm up phase.
	 * @param numberOfWarmUp default is 2000
	 */
	protected void setNumberOfWarmUps(Integer numberOfWarmUp) {
		this.numberOfWarmUps = numberOfWarmUp;
	}

	public Integer getNumberOfExecutions() {
		return numberOfExecutions;
	}

	/**
	 * This is the number of times the micro benchmark framework will 
	 * iterate over the execution phase.
	 * @param numberOfExecutions default is 1000
	 */
	protected void setNumberOfExecutions(Integer numberOfExecutions) {
		this.numberOfExecutions = numberOfExecutions;
	}

}
