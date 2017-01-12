/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jeremydyer.nifi.cli.domain;

import java.util.Map;

import com.jeremydyer.nifi.cli.configuration.Environment;
import com.jeremydyer.nifi.cli.configuration.NiFiCLIConfiguration;

/**
 * It makes me sad but there needs to be a few global variables until some refactoring happens to this object will hold
 * them all in a single place so its easier to nuke them later once some more details are further ironed out.
 *
 * Created by jdyer on 1/12/17.
 */
public class ShellContext {

    private static ShellContext instance = null;

    private String cliDisplay = "NiFi-CLI";
    private NiFiCLIConfiguration niFiCLIConfiguration;
    private Environment currentEnvironment;
    private Map<Environment, ServiceCache> environmentServiceCacheMap;

    protected ShellContext() {

    }

    public static ShellContext getInstance() {
        if(instance == null) {
            instance = new ShellContext();
        }
        return instance;
    }

    public NiFiCLIConfiguration getNiFiCLIConfiguration() {
        return niFiCLIConfiguration;
    }

    public void setNiFiCLIConfiguration(NiFiCLIConfiguration niFiCLIConfiguration) {
        this.niFiCLIConfiguration = niFiCLIConfiguration;
    }

    public Environment getCurrentEnvironment() {
        return currentEnvironment;
    }

    public void setCurrentEnvironment(Environment currentEnvironment) {
        this.currentEnvironment = currentEnvironment;
    }

    public String getCliDisplay() {
        return cliDisplay;
    }

    public void setCliDisplay(String cliDisplay) {
        this.cliDisplay = cliDisplay;
    }

    public Map<Environment, ServiceCache> getEnvironmentServiceCacheMap() {
        return environmentServiceCacheMap;
    }

    public void setEnvironmentServiceCacheMap(Map<Environment, ServiceCache> environmentServiceCacheMap) {
        this.environmentServiceCacheMap = environmentServiceCacheMap;
    }
}
