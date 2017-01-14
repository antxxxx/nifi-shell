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

package com.github.jdye64.nifi.shell.operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jdye64.nifi.shell.domain.ShellContext;

/**
 * Created by jdyer on 1/13/17.
 */
public class HistoryOperation {

    public static final Logger logger = LoggerFactory.getLogger(HistoryOperation.class);

    public void execute() {
        String historyFile = ShellContext.getInstance().getNifiShellHome() + "/history.log";
        logger.debug("Loading Nifi-Shell history from '{}'", historyFile);

        BufferedReader br = null;
        try {
            File hf = new File(historyFile);
            br = new BufferedReader(new FileReader(hf));

            List<String> history = new ArrayList<String>();
            String line = null;
            while ((line = br.readLine()) != null) {
                history.add(line);
            }

            logger.debug("{} history events found", history.size());

            for (int i = 0; i < history.size(); i++) {
                logger.info("{} - {}", i, history.get(i));
            }

        } catch (FileNotFoundException fnf) {
            logger.error("Unable to locate history file '{}'", historyFile);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ioe) {
                logger.error(ioe.getMessage());
            }
        }

    }
}
