# Improved REST API for Estonian Weather data

## Running the application
#### 1. [OPTION 1] Clone project with git
    $ git clone https://github.com/kkadalip/assignment-1ot-backend.git
    $ cd assignment-1ot-backend
#### 1. [OPTION 2] Clone project manually via download url
   * Download and unpack:
   https://github.com/kkadalip/assignment-1ot-backend/archive/master.zip  
#### 2. Open command prompt and navigate to project root
    (example) $ cd C:\Users\username\workspace\assignment-1ot-backend
#### 3. [OPTION 1] Build and run application AND SonarQube server using Docker-compose
    ($ docker-compose down)
    $ docker-compose up
    Open http://localhost:8090/
#### 3. [OPTION 2] Build and run application using Docker
    $ docker build -t weatherapp .
    $ docker run -p 8090:8090 weatherapp .
    Open http://localhost:8090/
#### 3. [OPTION 3] Build and run manually with provided gradle wrapper
    (optional) $ gradlew clean
    1) Building the application:
    $ gradlew build
    2) Running the application:
    $ gradlew bootRun
    Open http://localhost:8090/
    
    Building and starting with custom variables instead:
    $ gradlew bootRun -Pargs=--port=8093
    OR
    $ gradlew build && java -jar build/libs/assignment-iot-1.0.jar --mode=dev --port=8091
######	NB! Port 8090 will be used if "--port=" variable is not provided
### Configuration files    
    All environments:
    config/application.properties
    config/application.yml
## Analyze code quality via SonarQube (using Docker)
#### 1. Running Sonarqube server locally (if not already running via docker-compose):
    $ docker pull sonarqube
    $ docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube
    If it already exists then:
    $ docker start sonarqube
Open http://localhost:9000/ (username: admin password: admin)
#### 2. Running tests for test coverage (for SonarQube):
	$ gradlew test
#### 3. Analyzing project code
    Building and uploading project code analysis to local SonarQube server: 
    Open new command prompt window in project root eg:
    $ cd C:\Users\username\workspace\assignment-1ot-backend
    $ gradlew sonarqube
    
    For custom host url and port: 
    $ gradlew -Dsonar.host.url=http://localhost:9000 sonarqube
## Technology stack & frameworks in use
* Spring Boot - https://spring.io/projects/spring-boot
* Gradle 6.5 (optional if using docker) - https://gradle.org/install/
* Java 14 (optional if using docker) - https://jdk.java.net/14/
* Docker (optional) - https://docs.docker.com/docker-for-windows/install/
* REST API: Swagger - https://swagger.io/
* REST API specification: Swagger + OpenAPI - https://swagger.io/specification/
* REST API UI: Swagger UI - https://swagger.io/tools/swagger-ui/
* JAXB API for XML parsing - https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api/
* Logging: Log4J (configuration at config/log/log4j2.xml)
* Unit tests: Junit5 - https://junit.org/junit5/docs/current/user-guide/
* Docker compose - https://github.com/avast/gradle-docker-compose-plugin
### Application API docs (when application is running)
* http://localhost:8090/swagger-ui.html
* http://localhost:8090/v3/api-docs
#### Helpful guides:
* Setting up Spring Boot - https://spring.io/guides/gs/spring-boot/
* Serving web content - https://spring.io/guides/gs/serving-web-content/
* Server side testing - https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html
* Task scheduling - https://www.callicoder.com/spring-boot-task-scheduling-with-scheduled-annotation/
##### Source data and logic
* http://www.ilmateenistus.ee/teenused/ilmainfo/eesti-vaatlusandmed-xml/
* http://www.ilmateenistus.ee/ilma_andmed/xml/observations.php
* https://www.freemathhelp.com/wind-chill.html