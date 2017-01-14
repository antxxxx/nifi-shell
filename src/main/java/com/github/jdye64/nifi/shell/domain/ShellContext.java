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

package com.github.jdye64.nifi.shell.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jdye64.nifi.shell.configuration.Environment;
import com.github.jdye64.nifi.shell.configuration.NiFiCLIConfiguration;

/**
 * It makes me sad but there needs to be a few global variables until some refactoring happens to this object will hold
 * them all in a single place so its easier to nuke them later once some more details are further ironed out.
 *
 * Created by jdyer on 1/12/17.
 */
public class ShellContext {

    public Logger logger = LoggerFactory.getLogger(ShellContext.class);
    private static ShellContext instance = null;

    public static final String NIFI_SHELL_HOME_KEY = "NIFI_SHELL_HOME";
    private String nifiShellHome = null;
    private String cliDisplay = "NiFi-Shell";
    private NiFiCLIConfiguration niFiCLIConfiguration;
    private Environment currentEnvironment;
    private Map<Environment, ServiceCache> environmentServiceCacheMap;

    protected ShellContext() {
        this.environmentServiceCacheMap = new HashMap<Environment, ServiceCache>();
    }

    public static ShellContext getInstance() {
        if(instance == null) {
            instance = new ShellContext();
        }
        return instance;
    }

    public ServiceCache getServiceCacheForEnvironmentName(String environmentName) {
        Iterator<Environment> itr = this.environmentServiceCacheMap.keySet().iterator();
        ServiceCache cache = null;
        while (itr.hasNext()) {
            Environment env = itr.next();
            if (env.getEnvironmentName().equalsIgnoreCase(environmentName)) {
                cache = this.environmentServiceCacheMap.get(env);
                break;
            }
        }

        if (cache == null) {
            logger.warn("Service cache for environment %s does not exist yet so building.", environmentName);
            Environment e = this.niFiCLIConfiguration.getEnvironmentByName(environmentName);
            ServiceCache newCache = new ServiceCache(e);
            this.environmentServiceCacheMap.put(e, newCache);
            return newCache;
        } else {
            return cache;
        }
    }

    public String getNifiShellHome() {
        return nifiShellHome;
    }

    public void setNifiShellHome(String nifiShellHome) {
        this.nifiShellHome = nifiShellHome;
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
