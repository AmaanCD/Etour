# Stage 1: Build the application using JDK and Maven
FROM  maven:3.9.9-amazoncorretto-17-alpine AS build
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application and create the JAR file
RUN mvn clean package -DskipTests

# Stage 2: Run the application using JRE
FROM  amazoncorretto:17.0.14-alpine3.21
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/ETour-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]