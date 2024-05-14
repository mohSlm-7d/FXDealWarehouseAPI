# Dockerfile

# Use OpenJDK 17 as the base image
FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/fxdealwarehouseapi.jar /app/fxdealwarehouseapi.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "fxdealwarehouseapi.jar"]
