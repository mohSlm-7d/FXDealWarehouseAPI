#Dockerfile

# Use openjdk eclipse base image
FROM eclipse-temurin:17-jdk-alpine

VOLUME tmp

# Copy the packaged Spring Boot application JAR file into the container
COPY target/*.jar fxdealwarehouseapi.jar

# Command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "/fxdealwarehouseapi.jar"]

# Use port 8080
EXPOSE 9999:8080
