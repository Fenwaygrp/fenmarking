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
package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.fenwaygrp.fenmarking.Algorithm;

public class AlgorithmOne implements Algorithm {

    public static AtomicInteger warmups = new AtomicInteger(0);
    public static AtomicInteger executions = new AtomicInteger(0);
    public static AtomicInteger setupCount = new AtomicInteger(0);
    public static AtomicInteger tearDownCount = new AtomicInteger(0);
    public static Set<String> threadCount = Collections.synchronizedSet(new HashSet<String>());
    public static Boolean isSetUpCalled = false;
    public static Boolean isTearDownCalled = false;

    public void setUp(Map map) {
        isSetUpCalled = true;
        setupCount.incrementAndGet();
        map.put("AlgorithmOne", "true");
    }

    public void warmUp() {
        warmups.incrementAndGet();
    }

    public void execution() {
        try {
            Thread.sleep((long) (Math.random() * 10));
            executions.incrementAndGet();
            threadCount.add(Thread.currentThread().getName());
        } catch (InterruptedException e) {
        }
    }

    public void tearDown() {
        isTearDownCalled = true;
        tearDownCount.incrementAndGet();
    }

    public static void reset(){
        warmups.set(0);
        executions.set(0);
        isSetUpCalled = false;
        isTearDownCalled = false;
        setupCount.set(0);
        tearDownCount.set(0);
        threadCount.clear();
    }
    
}
