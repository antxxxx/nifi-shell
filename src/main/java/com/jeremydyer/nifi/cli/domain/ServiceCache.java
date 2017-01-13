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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeremydyer.nifi.cli.configuration.Environment;
import com.jeremydyer.nifi.cli.service.ConnectionsService;
import com.jeremydyer.nifi.cli.service.ConnectionsServiceImplementation;
import com.jeremydyer.nifi.cli.service.ControllerService;
import com.jeremydyer.nifi.cli.service.ControllerServiceImplementation;
import com.jeremydyer.nifi.cli.service.EnvironmentService;
import com.jeremydyer.nifi.cli.service.EnvironmentServiceImpl;
import com.jeremydyer.nifi.cli.service.FlowService;
import com.jeremydyer.nifi.cli.service.FlowServiceImplementation;
import com.jeremydyer.nifi.cli.service.InputPortsService;
import com.jeremydyer.nifi.cli.service.InputPortsServiceImplementation;
import com.jeremydyer.nifi.cli.service.OutputPortsService;
import com.jeremydyer.nifi.cli.service.OutputPortsServiceImplementation;
import com.jeremydyer.nifi.cli.service.ProcessGroups;
import com.jeremydyer.nifi.cli.service.ProcessGroupsImplementation;
import com.jeremydyer.nifi.cli.service.ProcessorsService;
import com.jeremydyer.nifi.cli.service.ProcessorsServiceImplementation;
import com.jeremydyer.nifi.cli.service.TemplateService;
import com.jeremydyer.nifi.cli.service.TemplateServiceImplementation;

/**
 * Cache for the multiple service instances for a particular environment.
 *
 * Created by jdyer on 1/12/17.
 */
public class ServiceCache {

    public static Logger logger = LoggerFactory.getLogger(ServiceCache.class);

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
        this.controllerService = new ControllerServiceImplementation(environment);
        this.environmentService = new EnvironmentServiceImpl(environment);
        this.flowService = new FlowServiceImplementation(environment);
        this.inputPortsService = new InputPortsServiceImplementation();
        this.outputPortsService = new OutputPortsServiceImplementation();
        this.processGroups = new ProcessGroupsImplementation(environment);
        this.processorsService = new ProcessorsServiceImplementation(environment);
        this.templateService = new TemplateServiceImplementation(environment);
    }


    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public ConnectionsService getConnectionsService() {
        return connectionsService;
    }

    public void setConnectionsService(ConnectionsService connectionsService) {
        this.connectionsService = connectionsService;
    }

    public ControllerService getControllerService() {
        return controllerService;
    }

    public void setControllerService(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    public EnvironmentService getEnvironmentService() {
        return environmentService;
    }

    public void setEnvironmentService(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    public FlowService getFlowService() {
        return flowService;
    }

    public void setFlowService(FlowService flowService) {
        this.flowService = flowService;
    }

    public InputPortsService getInputPortsService() {
        return inputPortsService;
    }

    public void setInputPortsService(InputPortsService inputPortsService) {
        this.inputPortsService = inputPortsService;
    }

    public OutputPortsService getOutputPortsService() {
        return outputPortsService;
    }

    public void setOutputPortsService(OutputPortsService outputPortsService) {
        this.outputPortsService = outputPortsService;
    }

    public ProcessGroups getProcessGroups() {
        return processGroups;
    }

    public void setProcessGroups(ProcessGroups processGroups) {
        this.processGroups = processGroups;
    }

    public ProcessorsService getProcessorsService() {
        return processorsService;
    }

    public void setProcessorsService(ProcessorsService processorsService) {
        this.processorsService = processorsService;
    }

    public TemplateService getTemplateService() {
        return templateService;
    }

    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
