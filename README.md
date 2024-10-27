# Project introducation
This project contains two services, one is incident management service built by spring boot 3, and web portal built by reactJs.

# Project structure
- Incident
    - incident-service: source code and Dockerfile for incident management service, service port: 8080
    - web-portalL source code and Dockerfile for web portal service, service port: 3000
    - docker-compose.yml: config file for docker-compose execution

# `docker-compose up --build`
To build and deploy incident management service and web portal to a docker environment, run docker-compose up --build under this folder, keeping the folder structure of the project.