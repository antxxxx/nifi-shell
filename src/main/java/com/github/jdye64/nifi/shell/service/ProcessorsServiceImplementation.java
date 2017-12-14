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

package com.github.jdye64.nifi.shell.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.nifi.web.api.ProcessorResource;
import org.apache.nifi.web.api.dto.ProcessorDTO;
import org.apache.nifi.web.api.entity.ProcessorEntity;
import org.apache.nifi.web.api.request.ClientIdParameter;
import org.apache.nifi.web.api.request.LongParameter;

import com.github.jdye64.nifi.shell.client.NiFiAPIClient;
import com.github.jdye64.nifi.shell.configuration.Environment;

/**
 * Created by jdyer on 4/8/16.
 */
public class ProcessorsServiceImplementation
    extends AbstractBaseService
    implements ProcessorsService {

    private static final String STOPPED = "STOPPED";
    private static final String RUNNING = "RUNNING";
    private static final String DISABLED = "DISABLED";

    public ProcessorsServiceImplementation(Environment environment) {
        client = new NiFiAPIClient(environment.getHostname(), environment.getPort());
    }

    public ProcessorDTO getProcessors(String clientId, String processorGroupId) {
//        String response = client.get("/controller/process-groups/" + processorGroupId + "/processors");
//        System.out.println("Response: " + response);
//        ObjectMapper mapper = new ObjectMapper();
//
//        ProcessorDTO resource = null;
//        try {
//            JSONObject root = new JSONObject(response);
//            resource = mapper.readValue(response, ProcessorDTO.class);
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }

        return null;
    }

    public ProcessorEntity stopProcessor(ProcessorEntity currentEntity) {
        try {
            currentEntity.getComponent().setState(STOPPED);
            return setProcessorState(currentEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private ProcessorEntity setProcessorState(ProcessorEntity stateToSet) {
        try {
            Method updateProcessorState = ProcessorResource.class.getMethod("updateProcessor",
                    HttpServletRequest.class, String.class, ProcessorEntity.class);
            Map<String, String> pathParams = new HashMap<String, String>();
            pathParams.put("id", stateToSet.getId());
            return (ProcessorEntity) client.put(ProcessorResource.class, updateProcessorState, stateToSet, pathParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ProcessorEntity deleteProcessor(ProcessorEntity entity) {
        try {
            Method deleteProcessor = ProcessorResource.class.getMethod("deleteProcessor",
                    HttpServletRequest.class, LongParameter.class, ClientIdParameter.class, String.class);
            Map<String, String> pathParams = new HashMap<String, String>();
            pathParams.put("id", entity.getId());
            Map<String, String> queryParams = new HashMap<String, String>();
            queryParams.put("clientid", entity.getRevision().getClientId());
            queryParams.put("version", new Long(entity.getRevision().getVersion()).toString());
            return (ProcessorEntity) client.delete(ProcessorResource.class, deleteProcessor, null, pathParams, queryParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
