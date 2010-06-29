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

public abstract class AbstractRunnable implements Runnable {

    private static FenmarkUtility fenmarkUtility = new FenmarkUtility();
    
    private Map params;
    private Algorithm instance;
    
    public AbstractRunnable(Class<? extends Algorithm> clazz, Map params){
        instance = fenmarkUtility.newInstance(clazz);
        this.params = params;
    }
    
    public void run() {
        instance.setUp(params);
        runInternal(instance);
        instance.tearDown();
    }
    
    protected abstract void runInternal(Algorithm instance);
    
}
