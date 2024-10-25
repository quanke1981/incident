#ARG REGISTRY=xxx/base-image
#FROM $REGISTRY/openjdk:17.0.13-jdk

COPY taget/incident-0.0.1-SNAPSHOT.jar /usr/local/incident
#COPY application-docker.yml /usr/local/incident

EXPOSE 8080
WORKDIR /usr/local/incident
CMD["java", "-jar", "incident-0.0.1-SNAPSHOT.jar"]