# Use an OpenJDK 17 image based on Alpine (lightweight image)
FROM eclipse-temurin:21-jdk-alpine

# Set a volume for temporary files (common for Java apps)
VOLUME /tmp

# Copy the specific JAR file from the target directory into the container
COPY target/sport-app-api-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (if your Spring Boot app uses this default port)
EXPOSE 8096

# Set the entry point for the container to run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
