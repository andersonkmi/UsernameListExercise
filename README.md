# UserNameListMicroservice
This is a very simple microservice that receives a user name and verifies it is free to use. If not, suggests up to 14 possible alternative user names.

It also verifies for invalid/restricted words inside the user name and makes suggestions based on the user name.

## Pre-requisites
The pre-requisites for this application are:
+ Java 8
+ MySQL 5.7 or MariaDB
+ Maven 3.3.9 or higher

Maven and Java 8 are used for building the application and for executing it. The application makes use of a MySQL/MariaDB database to register the user names and the restricted words for the application.

## Description
This microservice is a Spring Boot based application that launches an embedded Tomcat listening for requests at the port 8080 (by default). In the following sections it will be described the building process and how to send requests to it.

## Database dependency
This application depends on a very simple database in order to execute it and for unit testing purposes running locally to the development environment. The following details regarding the database are provided:
+ MySQL/MariaDB running locally listening on the default port 3306
+ database name: <strong>usernamelist</strong>
+ Database user name: <strong>anderson</strong>
+ Database user password: <strong>anderson</strong>

In the project there is a script named <strong>sql-scripts.sql</strong> inside the folder <strong>database</strong> that can be executed to create the required tables and populate with the required records to execute the unit-tests successfully.

## Build process
The build process can be done in two different ways: with or without running the unit-tests. To build the application without the unit-tests, simply execute the following command at the location where the <strong>pom.xml</strong> is located (root folder of the project):
```
mvn clean package -DskipTests=true
```
The command above will perform the compilation and building process skipping the unit-tests.

If you want to build the application running the unit-tests, execute the following command:
```
mvn clean package
```
In this case the unit tests will be built and executed during the build process. It is important to note that in this case, the database must be configured based on the information provided in the previous section.

Once the build process is finished successfully, there will be file named <strong>UserNameListMicroservice-1.0.0.0.jar</strong> in the <strong>target</strong> folder.

## Running the application
