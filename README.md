# Supernova

A reference application for Nebula. 

made with start.spring.io with maven/kotlin/jar chosse spring-web and spring-actualty


## Build docker image
`mvn spring-boot:build-image` -Dversion=main-<buildNumber>

## Pipeline

Currently azure-pipeline is used with a really simple workflow
 - checkout 
 - compile, run teste and report surefire
 - build a docker image with main-<BuildId>
 - push the image to docker registry

