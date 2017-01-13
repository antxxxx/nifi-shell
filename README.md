<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at
      http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. -->

# NiFi-Shell

- [Features](#features)
- [Running](#running)
- [Shell Commands](#shell-commands)
- [Terminoloy](#terminology)

## Features

* Execute Shell files from the CLI that has a series of commands
* Handle operations for NiFi cluster
* Support multiple environments
* Promote workflows from Environment -> Environment

## Running
Currently the shell is in development so requires you build the shell before running it. The shell can be build by running ```mvn clean install package -DskipTests``` then
the shell can be ran using ```java -jar $SHELL_HOME/target/nifi-shell-1.2.0-SNAPSHOT.jar```

There will certainly be more convenient scripts to do this later but it is what it is for right now.

## Shell Commands

| Command | Description | Min Version | Demo |
| :-------: |:--------------------:| :-----: | :-------------:|
| [deployment](#deployment) | Promotes a NiFi template, environment variables, configurations, and nars from one environment to another. | 1.2.0 | [Youtube](#features) |
| [lenvs](#lenvs) | Lists all of the environments that are currently configured for this NiFi-Shell instance | 1.2.0 | [Youtube](#features) |
| [ltemplates](#ltemplates) | Lists all of the templates from the current working environment.| 1.2.0 | [Youtube](#features) |
| [help](#help) | Lists all of the available commands to the shell user | 1.2.0 | None |
| [senv](#senv) | Sets the current working environment | 1.2.0 | [Youtube](#features) |
| [status](#status) | Gets the status via the NiFi REST API for the current working environment. | 1.2.0 | [Youtube](#features) |
| [quit](#quit) | Exits the NiFi-Shell | 1.2.0 | None |


###deployment

```deployment``` is used to initiate a new deployment from one NiFi environment to another. A deployment can be a rather complicated process if handled manually since
a number of configurations must be manually checked in each environment which can lead to user error. A deployment seamlessly handles several aspects of making
a Apache NiFi environment promotion. Including ...

- Copying desired NiFi template XML file from source environment to destination.
- Comparing available processors in source environment with that of the destination.
    - If the processors differ the shell user will be presented to option to abort or resolve the missing requirements.
- Comparing environment variables that are set in the source environment with those in the destination. This helps reduce the risks of missing environment
variables that are referenced by NiFi expression language snippets in the promoted template.
- Comparing NiFi configuration values from the $NIFI_HOME/conf/nifi.properties file. This helps ensure that the template will run as expected on the promoted
environment in the same successful manner as it was running on the lesser environment.
- Rollbacks. The deployment process will keep a transaction log of changes and promotions. If something does go wrong the shell user will have the option to rollback the deployment.
- Deployment Log. A complete log of all changes made during the deployment are logged to a special file that can later be referenced or stored elsewhere for reference.

###lenvs
Lists all of the environments that are configured in this instance of NiFi-Shell. The list of environments are loaded by default from ```~/.nifishell/conf.json```. This
is the default configuration file and if you add any new environments they will be persisted to this file so that upon next execution of NiFi-Shell they are loaded
and prepared for interaction.

###ltemplates
Lists all of the templates from the current working environment. The templates are pulled from whatever environment is currently set.

###help
Pretty self explanatory ...

###senv
Sets the current working environment that all commands requiring a current environment will use. The shell prompt will change to reflect the environment
that is your current working environment to serve as a reminder and for convenience.

###status
Gets the status from the NiFi REST API for the current working environment.

###quit
Exits the NiFi-Shell.

##Terminology

- **environment** - An environment is a single instance or cluster of NiFi nodes. An environment shares a common list of NiFi templates with 0 or more templates
running. An environment also shares a common configuration instance and variables from an optional configured variable registry.
- **template** - An object in Apache NiFi that represents a piece of workflow logic in a configured environment.