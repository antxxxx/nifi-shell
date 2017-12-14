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

import org.apache.nifi.web.api.ConnectionResource;
import org.apache.nifi.web.api.ProcessorResource;
import org.apache.nifi.web.api.dto.EntityFactory;
import org.apache.nifi.web.api.entity.ConnectionEntity;
import org.apache.nifi.web.api.entity.ConnectionsEntity;

import com.github.jdye64.nifi.shell.client.NiFiAPIClient;
import com.github.jdye64.nifi.shell.configuration.Environment;

public class ConnectionsServiceImplementation
    extends AbstractBaseService
    implements ConnectionsService {

    private EntityFactory entityFactory;

    public ConnectionsServiceImplementation(Environment environment) {
        client = new NiFiAPIClient(environment.getHostname(), environment.getPort());
        this.entityFactory = new EntityFactory();
    }

    public ConnectionsEntity getConnections(String clientId, String processorGroupId) {
        try {
            Method getConnectionMethod = ProcessorResource.class.getMethod("getConnection", String.class);
            Map<String, String> pathParams = new HashMap<String, String>();
            pathParams.put("id", processorGroupId);
            return (ConnectionsEntity) client.get(ConnectionResource.class, getConnectionMethod, null, pathParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ConnectionEntity deleteConnection(String clientId, String connectionId) {
        try {
            Method deleteConnectionMethod = ConnectionResource.class.getMethod("deleteConnection", String.class);
            Map<String, String> pathParams = new HashMap<String, String>();
            pathParams.put("id", connectionId);
            return (ConnectionEntity) client.get(ConnectionResource.class, deleteConnectionMethod, null, pathParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
