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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jdye64.nifi.shell.configuration.Environment;
import com.github.jdye64.nifi.shell.service.ConnectionsService;
import com.github.jdye64.nifi.shell.service.ConnectionsServiceImplementation;
import com.github.jdye64.nifi.shell.service.ControllerService;
import com.github.jdye64.nifi.shell.service.ControllerServiceImplementation;
import com.github.jdye64.nifi.shell.service.EnvironmentService;
import com.github.jdye64.nifi.shell.service.EnvironmentServiceImpl;
import com.github.jdye64.nifi.shell.service.FlowService;
import com.github.jdye64.nifi.shell.service.FlowServiceImplementation;
import com.github.jdye64.nifi.shell.service.InputPortsService;
import com.github.jdye64.nifi.shell.service.InputPortsServiceImplementation;
import com.github.jdye64.nifi.shell.service.OutputPortsService;
import com.github.jdye64.nifi.shell.service.OutputPortsServiceImplementation;
import com.github.jdye64.nifi.shell.service.ProcessGroups;
import com.github.jdye64.nifi.shell.service.ProcessGroupsImplementation;
import com.github.jdye64.nifi.shell.service.ProcessorsService;
import com.github.jdye64.nifi.shell.service.ProcessorsServiceImplementation;
import com.github.jdye64.nifi.shell.service.TemplateService;
import com.github.jdye64.nifi.shell.service.TemplateServiceImplementation;

/**
 * Cache for the multiple service instances for a particular environment.
 *
 * Created by jdyer on 1/12/17.
 */
public class ServiceCache {

    public Logger logger = LoggerFactory.getLogger(ServiceCache.class);

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
        this.connectionsService = new ConnectionsServiceImplementation(environment);
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
