# Supernova

A reference application for Nebula. 

 - https://github.com/lectra-tech/azurite-testcontainers was used to set up BlobStorage

## Develop locally
`docker-compose up` to start postgres and blob storage locally
Start the applicaion in your IDE or in terminal

## Build docker image
`mvn spring-boot:build-image -Dversion=new-version`

## Pipeline

Currently azure-pipeline is used with a really simple workflow
 - checkout 
 - compile, run teste and report surefire
 - build a docker image with main-<BuildId>
 - push the image to docker registry

Github actions are also supported with the same functionality as azure pipeline
