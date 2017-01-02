# User name list service
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

In the project there is a script named <strong>sql-scripts.sql</strong> inside the folder <strong>database</strong> that can be executed to create the required tables and populate with the required records to execute the unit-tests successfully. In that script there is also the commands required to create the database user and configure the privileges for it.

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
Once the application is successfully built, in order to execute it just issue the following command inside the <strong>target</strong> folder:
```
java -jar UserNameListMicroservice-1.0.0.0jar
```
Upon executing it, the application is launched at the port 8080. See below an example of the output when executing it:
``` bash
2017-01-02 21:52:39.366  INFO 1835 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/userNameService/checkUserName/{userName}],methods=[GET]}" onto public java.lang.String org.techstuff.auth.controller.UserNameListController.checkUserName(java.lang.String)
2017-01-02 21:52:39.371  INFO 1835 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-01-02 21:52:39.372  INFO 1835 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-01-02 21:52:39.440  INFO 1835 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-01-02 21:52:39.441  INFO 1835 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-01-02 21:52:39.527  INFO 1835 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-01-02 21:52:39.962  INFO 1835 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-01-02 21:52:40.089  INFO 1835 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-01-02 21:52:40.097  INFO 1835 --- [           main] .t.a.UsernameListMicroserviceApplication : Started UsernameListMicroserviceApplication in 5.809 seconds (JVM running for 6.563)
```
## Invoking the service
