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

package com.jeremydyer.nifi.cli.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.nifi.web.api.FlowResource;
import org.apache.nifi.web.api.dto.EntityFactory;
import org.apache.nifi.web.api.entity.AboutEntity;
import org.apache.nifi.web.api.entity.FlowConfigurationEntity;
import org.apache.nifi.web.api.entity.ProcessGroupFlowEntity;
import org.apache.nifi.web.api.entity.ProcessorTypesEntity;
import org.apache.nifi.web.api.entity.TemplatesEntity;

import com.jeremydyer.nifi.cli.client.NiFiAPIClient;

/**
 * Created by jdyer on 1/12/17.
 */
public class FlowServiceImplementation
    extends AbstractBaseService
    implements FlowService {

    private EntityFactory entityFactory;

    public FlowServiceImplementation(String server, String port) {
        client = new NiFiAPIClient(server, port);
        this.entityFactory = new EntityFactory();
    }

    public AboutEntity getAboutInfo() {
        AboutEntity resp = null;
        try {
            Method processorTypesMethod = FlowResource.class.getMethod("getAboutInfo");
            resp = (AboutEntity) client.get(FlowResource.class, processorTypesMethod, null, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return resp;
    }

    public ProcessGroupFlowEntity getFlow(String processGroupId) {
        ProcessGroupFlowEntity resp = null;
        try {
            Method processorTypesMethod = FlowResource.class.getMethod("getFlow", String.class);
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", processGroupId);
            resp = (ProcessGroupFlowEntity) client.get(FlowResource.class, processorTypesMethod, null, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return resp;
    }

    public FlowConfigurationEntity getFlowConfig() {
        FlowConfigurationEntity response = null;
        try {
            Method processorTypesMethod = FlowResource.class.getMethod("getFlowConfig");
            response = (FlowConfigurationEntity) client.get(FlowResource.class, processorTypesMethod, null, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return response;
    }

    public ProcessorTypesEntity getProcessorTypes() {
        ProcessorTypesEntity response = null;
        try {
            Method processorTypesMethod = FlowResource.class.getMethod("getProcessorTypes");
            response = (ProcessorTypesEntity) client.get(FlowResource.class, processorTypesMethod, null, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return response;
    }

    public TemplatesEntity getAllTemplates() {
        TemplatesEntity response = null;
        try {
            Method exportTemplate = FlowResource.class.getMethod("getTemplates");
            response = (TemplatesEntity) client.get(FlowResource.class, exportTemplate, null, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return response;
    }
}
