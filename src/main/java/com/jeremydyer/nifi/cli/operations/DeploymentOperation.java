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

package com.jeremydyer.nifi.cli.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.nifi.web.api.dto.TemplateDTO;
import org.apache.nifi.web.api.entity.FlowConfigurationEntity;
import org.apache.nifi.web.api.entity.ProcessGroupFlowEntity;
import org.apache.nifi.web.api.entity.ProcessorTypesEntity;
import org.apache.nifi.web.api.entity.TemplateEntity;
import org.apache.nifi.web.api.entity.TemplatesEntity;

import com.jeremydyer.nifi.cli.service.FlowService;
import com.jeremydyer.nifi.cli.service.FlowServiceImplementation;
import com.jeremydyer.nifi.cli.service.ProcessGroups;
import com.jeremydyer.nifi.cli.service.ProcessGroupsImplementation;
import com.jeremydyer.nifi.cli.service.TemplateService;
import com.jeremydyer.nifi.cli.service.TemplateServiceImplementation;

/**
 * Operation for deploying a template from a lower environment to a higher environment.
 *
 * Created by jdyer on 1/12/17.
 */
public class DeploymentOperation {

    public void execute() {
        try {
            //Select the environment that you want to list the templates to promote for.

            //List all available templates
            FlowService flowService = new FlowServiceImplementation("localhost", "8080");
            TemplatesEntity templates = flowService.getAllTemplates();
            TemplateEntity templateEntity = printTemplatesToPromote(templates);

            //Download Template from current environment.
            TemplateService templateService = new TemplateServiceImplementation("localhost", "8080");
            TemplateDTO currentEnvTemplate = templateService.downloadTemplate(templateEntity.getId());

            //Download properties from current environment.

            //Download properties from promote environment.

            //Compare properties.

            //Log findings.

            //Download processors/nars from current environment.
            ProcessorTypesEntity currentEnvProcessors = flowService.getProcessorTypes();
            System.out.println("Current Environment Processors");

            //Download processors/nars from promote environment.
            FlowService promoteFlowService = new FlowServiceImplementation("localhost", "8090");
            ProcessorTypesEntity promoteEnvProcessors = flowService.getProcessorTypes();
            System.out.println("Promote Environment Processors");

            //Compare processors.
            compareAvailableProcessors(currentEnvProcessors, promoteEnvProcessors);

            //Log findings.

            //Download configurations from current environment.
            FlowConfigurationEntity currentEnvConfigs = flowService.getFlowConfig();

            //Download configurations from promote environment.
            FlowConfigurationEntity promoteEnvConfigs = promoteFlowService.getFlowConfig();

            //Compare configurations.
            compareEnvironmentsConfigurations(currentEnvConfigs, promoteEnvConfigs);

            //Log findings

            //Overall status log findings.

            //Prompt for options if conflicts found.

            //Finalize deployment and push changes to promote environment.
            ProcessGroupFlowEntity promoteRootPG = promoteFlowService.getFlow("root");
            ProcessGroups processGroups = new ProcessGroupsImplementation("localhost", "8090");

            TemplateEntity promotedTemplateEntity = processGroups.uploadTemplate(promoteRootPG.getProcessGroupFlow().getId(), currentEnvTemplate);

            //Log deployment to archive database.
            System.out.println("Logging deployment to archive database");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void compareAvailableProcessors(ProcessorTypesEntity currentEnv, ProcessorTypesEntity promoteEnv) {
        System.out.println("Comparing processors from current environment with those for promote environment.");
        System.out.println("Current Environment has: " + currentEnv.getProcessorTypes().size() + " processors");
        System.out.println("Promote Environment has: " + promoteEnv.getProcessorTypes().size() + " processors");
    }

    private void compareEnvironmentsConfigurations(FlowConfigurationEntity currentEnvConfig, FlowConfigurationEntity promoteEnvConfig) {
        System.out.println("Comparing current environment configuration with promote environment configuration");
    }

    /**
     * Prints all of the templates that are available to promote and then allows the user to choose which one
     * they want to promote.
     *
     * @param templatesEntity
     */
    private TemplateEntity printTemplatesToPromote(TemplatesEntity templatesEntity) throws IOException {
        TemplateEntity promote = null;

        if (templatesEntity != null) {
            TemplateEntity[] templates = templatesEntity.getTemplates().toArray(new TemplateEntity[templatesEntity.getTemplates().size()]);

            boolean invalid = true;
            while (invalid) {
                System.out.println("Please select a Template you would like to promote: ");
                for (int i = 0; i < templates.length; i++) {
                    System.out.print(i + ") ");
                    System.out.println(templates[i].getTemplate().getName());
                }

                System.out.print("\nTemplate # > ");
                InputStreamReader in = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(in);
                String input = br.readLine();

                //Validate the input and then grab the TemplateEntity and return it.
                int selection = Integer.parseInt(input);
                if (selection >= 0 && selection < templates.length) {
                    promote = templates[selection];
                    invalid = false;
                } else {
                    System.out.println("Please enter a valid template. " + 0 + " - " + templates.length);
                }
            }

        } else {
            System.err.println("There are no templates currently setup on the nifi instance! Throw error here!");
        }

        return promote;
    }
}
