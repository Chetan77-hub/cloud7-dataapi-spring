# Use official OpenJDK base image
FROM openjdk:21-jdk

# Set working directory
WORKDIR /app

# Copy the jar file
COPY target/spring-boot-employee-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
