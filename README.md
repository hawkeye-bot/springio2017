# Making the most of Spring Boot: adapting to your environment!
Welcome to the hands-on lab for Spring Boot. In this repository, there are a total of 5 branches available:
* master
* lab1
* lab2
* lab3
* solution

For this workshop, the lab-branches are incremental. This means that when you've finished lab1 (or you are stuck), you can simply checkout the next lab-branch to get a clean starting point for the next lab. As a result, the solution branch contains the total solution of all labs in this workshop.

The powerpoint that we'll be using during the workshop is available in all branches.

If you want to switch between labs, you can use the git checkout command:
* git checkout <master/lab1/lab2/lab3/solution>

## Artemis
The software in the labs runs based on an Artemis JMS server. Included in the branches is a standalone installation of Artemis 2.0.0. You can start it using the following steps:

1. Open a terminal in ${repository_home}/apache-artemis-2.0.0/springio/bin
2. Run `artemis run`

## Maven
We've assumed that you can use an available internet connection to download any required maven depencies you need for the projects. However, in case this doesn't work for whatever reason, there is a complete repository in each branch at ${repository_home}/repository. You can either choose to copy this on top of your own maven repository, or simply point your maven installation to this repository folder using the settings.xml
