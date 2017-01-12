# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements. See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership. The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License. You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied. See the License for the
# specific language governing permissions and limitations
# under the License.
#

FROM java:8
MAINTAINER Jeremy Dyer <jeremydyer@apache.org>

ARG NIFI_VERSION
ARG NIFI_SHELL_BINARY

ENV NIFI_SHELL_BASE_DIR /opt/nifishell
ENV SHELL_JAR $NIFI_SHELL_BASE_DIR/$NIFI_SHELL_BINARY

RUN mkdir -p $NIFI_SHELL_BASE_DIR
ADD $NIFI_SHELL_BINARY $NIFI_SHELL_BASE_DIR/

# Startup NiFi
CMD java -jar $SHELL_JAR