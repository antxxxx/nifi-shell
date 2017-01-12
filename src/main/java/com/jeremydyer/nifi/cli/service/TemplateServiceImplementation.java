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

import org.apache.nifi.web.api.TemplateResource;
import org.apache.nifi.web.api.dto.EntityFactory;
import org.apache.nifi.web.api.dto.TemplateDTO;
import org.apache.nifi.web.api.entity.TemplateEntity;

import com.jeremydyer.nifi.cli.client.NiFiAPIClient;

/**
 * Created by jdyer on 1/11/17.
 */
public class TemplateServiceImplementation
        extends AbstractBaseService
        implements TemplateService {

    private EntityFactory entityFactory;

    public TemplateServiceImplementation(String server, String port) {
        client = new NiFiAPIClient(server, port);
        this.entityFactory = new EntityFactory();
    }

    public TemplateEntity deleteTemplate(String templateId) {
        return null;
    }

    public TemplateDTO downloadTemplate(String templateId) {

        TemplateDTO response = null;
        try {
            Method exportTemplate = TemplateResource.class.getMethod("exportTemplate", String.class);
            Map<String, String> pathParams = new HashMap<String, String>();
            pathParams.put("id", templateId);
            response = (TemplateDTO) client.get(TemplateResource.class, exportTemplate, null, pathParams);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return response;
    }
}
