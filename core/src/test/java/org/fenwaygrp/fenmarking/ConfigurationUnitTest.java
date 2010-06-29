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

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationUnitTest {
    
    @Test(expected=AssertionError.class)
    public void shouldThrowNullAssertionForNumberOfWarmUps() throws Exception {
        new DummyConfiguration(null, 1);
    }
    
    @Test(expected=AssertionError.class)
    public void shouldThrowNullAssertionForNumberOfExecutions() throws Exception {
        new DummyConfiguration(null, 1);
    }
    
    @Test(expected=AssertionError.class)
    public void shouldThrowInvalidValudForNumberOfWarmUps() throws Exception {
        new DummyConfiguration(-1, 1);
    }
    
    @Test(expected=AssertionError.class)
    public void shouldThrowInvalidValudForNumberOfExecutions() throws Exception {
        new DummyConfiguration(1, -1);
    }
    
}


class DummyConfiguration extends Configuration {

    public DummyConfiguration(Integer numberOfWarmUps, Integer numberOfExecutions) {
        super(numberOfWarmUps, numberOfExecutions);
    }

    
    
}
