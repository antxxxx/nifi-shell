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

import org.apache.nifi.web.api.dto.ProcessorDTO;
import org.apache.nifi.web.api.entity.ProcessorEntity;

/**
 * Created by jdyer on 4/8/16.
 */
public interface ProcessorsService {

    /**
     * Gets all processors
     *
     * @param clientId
     *
     * @param processorGroupId
     *  The id of the process group that is the parent of the requested resource(s). If the desired process group is the root group an alias 'root' may be used as the process-group-id.
     *
     * @return
     */
    ProcessorDTO getProcessors(String clientId, String processorGroupId);

    ProcessorEntity stopProcessor(ProcessorEntity currentEntity);

    ProcessorEntity deleteProcessor(ProcessorEntity entity);


}
