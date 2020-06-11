# 1oT assignment backend
Server side code for 1oT assignment  

## Technology stack
Gradle 6.5  
Java 14  
Swagger + OpenAPI  

## Running the application
### 1. Clone with git
    git clone https://github.com/kkadalip/assignment-1ot-backend.git
    cd assignment-1ot-backend
### 2. Build & Run with docker
    gradlew build
    docker build -t iot/weatherapp .
    docker run -p 8090:8090 iot/weatherapp .
### 2. (alternatively) Build and run manually with gradle
    gradlew build && java -jar build/libs/assignment-1ot-backend-0.1.0.jar