# incident service

# Referenced external library on top of JDK:
Please refer to pom.xml

# To build the project
mvn clean install

# To run the project
mvn spring-boot:run

# Visit the swagger ui for viewing service APIs
use browser to open http://{hostname:port}/swagger-ui/index.html

# To perform stress testing by apache JMeter
1. Deploy and start the incident service
2. Open JMeter and create a thread group, with setting of number of threads, ramp-up time, and loop count to control the load.
2. Add an HTTP request sampler for the APIs of the deployed incident service 
3. Add listeners to analyze the result
4. Run the test
