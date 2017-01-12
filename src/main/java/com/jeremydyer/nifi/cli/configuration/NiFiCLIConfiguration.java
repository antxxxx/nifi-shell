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

package com.jeremydyer.nifi.cli.configuration;

import java.io.File;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Parent object holding all of the required configurations from the NiFi-CLI application
 * including environments, passwords, intervals, operations, users, etc
 *
 * Created by jdyer on 5/11/16.
 */
public class NiFiCLIConfiguration {

    public static Logger logger = LoggerFactory.getLogger(NiFiCLIConfiguration.class);
    private String configurationVersion;
    private ArrayList<Environment> environments;

    public NiFiCLIConfiguration() {
        //TODO: set these to something much more intelligent by pulling from the instances themselves.
        this.configurationVersion = "0.1";
        environments = new ArrayList<Environment>();
        environments.add(new Environment("dev"));
        environments.add(new Environment("qa"));
        environments.add(new Environment("prod"));
    }

    public boolean doesEnvironmentExist(String envName) {
        boolean exists = false;
        for (Environment env : environments) {
            if (env.getEnvironmentName().equals(envName)) {
                exists = true;
            }
        }
        return exists;
    }

    public Environment getEnvironmentByName(String envName) {
        for (Environment env : environments) {
            if (env.getEnvironmentName().equals(envName)) {
                return env;
            }
        }
        return null;
    }

    /**
     * Loads the NiFiCLIConfiguration file. If the file or directory does not exist they will be created.
     *
     * @return
     * @throws Exception
     */
    public static NiFiCLIConfiguration loadNiFiCLIConfiguration(String configFilePath) throws Exception {
        File confFile = new File(configFilePath);
        ObjectMapper mapper = new ObjectMapper();

        //Make sure that the file exists
        if (!confFile.exists()) {
            System.out.println("Unable to find NiFi CLI configuration file: '" + configFilePath
                    + "' creating empty one now" );

            if (!confFile.getParentFile().exists()) {
                if (!confFile.getParentFile().mkdirs()) {
                    System.out.println("Failed to create NiFi-CLI configuration directory at: '" + confFile.getParent()
                            + "' unable to continue");
                    throw new Exception("Unable to create non-existant NiFi-CLI configuration directory at '" + confFile.getParent() + "'");
                }
            }

            //Create an empty NiFiCLIConfiguration file and write it to disk
            NiFiCLIConfiguration emptyConf = new NiFiCLIConfiguration();
            mapper.writeValue(confFile, emptyConf);
        }

        //Load the NiFiCLIConfiguration file.
        logger.debug("Loading NiFi-Shell configuration file %s", configFilePath);
        return mapper.readValue(confFile, NiFiCLIConfiguration.class);
    }

    public String getConfigurationVersion() {
        return configurationVersion;
    }

    public void setConfigurationVersion(String configurationVersion) {
        this.configurationVersion = configurationVersion;
    }

    public ArrayList<Environment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(ArrayList<Environment> environments) {
        this.environments = environments;
    }
}
