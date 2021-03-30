# Supernova

A reference application for Nebula. 

made with start.spring.io with maven/kotlin/jar chosse spring-web and spring-actuator. Added postgres and testcontainer dependencies afterwards

run `docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres:11.7-alpine` to start the postgres before starting the application

## Build docker image
`mvn spring-boot:build-image -Dversion=new-version`

## Pipeline

Currently azure-pipeline is used with a really simple workflow
 - checkout 
 - compile, run teste and report surefire
 - build a docker image with main-<BuildId>
 - push the image to docker registry


Github actions are also supported with the same functionality as azure pipeline

