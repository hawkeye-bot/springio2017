# Artemis
The software in the labs runs based on an Artemis JMS server. Included in the repository is a standalone installation of Artemis 2.0.0. You can start it using the following steps:

1. Open a terminal in ${repository_home}/apache-artemis-2.0.0/springio/bin
2. Run `artemis run`

In case you need it, the username/password/role setup on the Artemis installation is _springio_

## Maven
We've assumed that you can use an available internet connection to download any required maven depencies you need for the projects. However, in case this doesn't work for whatever reason, there is a complete repository in each branch at ${repository_home}/repository. You can either choose to copy this on top of your own maven repository, or simply point your maven installation to this repository folder using the settings.xml
