FROM openjdk:17

VOLUME /tmp
COPY taget/incident-0.0.1-SNAPSHOT.jar /usr/local/incident

EXPOSE 8080
WORKDIR /usr/local/incident
CMD["java", "-jar", "incident-0.0.1-SNAPSHOT.jar"]