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

import java.util.List;

import org.apache.nifi.web.api.entity.ConnectionEntity;
import org.apache.nifi.web.api.entity.ConnectionsEntity;
import org.apache.nifi.web.api.entity.ProcessorEntity;

public interface ConnectionsService {

    /**
     * Gets all connections for a ProcessGroup
     *
     * @param clientId
     *
     * @param processorGroupId
     *  The id of the process group that is the parent of the requested resource(s). If the desired process group is the root group an alias 'root' may be used as the process-group-id.
     *
     * @return
     */
    ConnectionsEntity getConnections(String clientId, String processorGroupId);

    ConnectionEntity deleteConnection(String clientId, String connectionId);

    List<ConnectionEntity> getUpstreamConnectionsForProcessor(
            ConnectionsEntity allConnections, ProcessorEntity processorEntity);

    List<ConnectionEntity> getDownstreamConnectionsForProcessor(
            ConnectionsEntity allConnections, ProcessorEntity processorEntity);
}
