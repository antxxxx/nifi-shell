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

package com.github.jdye64.nifi.shell.exception;

/**
 * Certain operations require a "Current Working Environment" be set such as deploying from one environment
 * to another. This exception is thrown if the user has failed to set a current working environment.
 *
 * Created by jdyer on 1/12/17.
 */
public class EnvironmentNotSetException
    extends Exception {

    public EnvironmentNotSetException(String message) {
        super(message);
    }
}
