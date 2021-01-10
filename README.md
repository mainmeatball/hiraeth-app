# Spring Boot multi-module web application

## About
This is a pet-project for training the fundamental features of Spring Boot application.

## Requirements
This demo is build with Maven 4.0.0 and Java 11.

## Usage
The application is build like a constructor by adding modules as maven `pom.xml` dependencies. For example, the `demo` 
module is an example of average application that uses all acceptable modules. To build and run the demo application do the
following:
- First of all, if you want to use database in the application, you can set postgres database by yourself on port `5434`
with user `hiraeth`, password `hiraeth` and database `hiraeth`.
An alternative would be to use postgres `Docker` image to create the container. The steps are:
- [install the docker](https://docs.docker.com/get-docker/)
- pull the postgres image like `docker pull postgres`
- run the shell script
```shell script
sudo chmod +x docker-hiraeth-run.sh
./docker-hiraeth-run.sh 5434
``` 


- move to the demo module `cd demo`
- build module using maven `mvn package`
- run the jar `java -jar target/demo-{$version}-exec.jar`

