# incident service

# Referenced external library on top of JDK:
Please refer to pom.xml

# To deploy the service to docker environment
If you would like to deploy the service to docker environment:
   - with compiled .jar file, run docker build -f ./Dockerfile -t incident-service:latest
   - with source code, run docker build -f ./Dockerfile-build -t incident-service:latest