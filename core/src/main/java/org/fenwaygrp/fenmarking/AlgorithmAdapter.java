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
 * This is a convenience adapter class provides an empty setUp/tearDown implementation and 
 * has the warmUp delegate to the execution operation.  Use this adapter class for the simple 
 * case where you only need to provide an implementation in the execute method.
 * @author Saad Khawaja
 *
 */
public abstract class AlgorithmAdapter implements Algorithm {

    /**
     * A do nothing implementation
     */
    @SuppressWarnings("unchecked")
    public void setUp(Map params) {
    }

    /**
     * A do nothing implementation
     */
    public void tearDown() {
    }

    /**
     * The warmup delegates to the execution method.
     */
    public void warmUp() {
        execution();
    }

}
