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

import com.jeremydyer.nifi.cli.configuration.Environment;
import com.jeremydyer.nifi.cli.service.ConnectionsService;
import com.jeremydyer.nifi.cli.service.ConnectionsServiceImplementation;
import com.jeremydyer.nifi.cli.service.ControllerService;
import com.jeremydyer.nifi.cli.service.EnvironmentService;
import com.jeremydyer.nifi.cli.service.FlowService;
import com.jeremydyer.nifi.cli.service.InputPortsService;
import com.jeremydyer.nifi.cli.service.OutputPortsService;
import com.jeremydyer.nifi.cli.service.ProcessGroups;
import com.jeremydyer.nifi.cli.service.ProcessorsService;
import com.jeremydyer.nifi.cli.service.TemplateService;

/**
 * Cache for the multiple service instances for a particular environment.
 *
 * Created by jdyer on 1/12/17.
 */
public class ServiceCache {

    private Environment environment;
    private ConnectionsService connectionsService;
    private ControllerService controllerService;
    private EnvironmentService environmentService;
    private FlowService flowService;
    private InputPortsService inputPortsService;
    private OutputPortsService outputPortsService;
    private ProcessGroups processGroups;
    private ProcessorsService processorsService;
    private TemplateService templateService;

    public ServiceCache(Environment environment) {
        this.environment = environment;
        this.connectionsService = new ConnectionsServiceImplementation();
    }
}
