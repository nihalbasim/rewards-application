# Stage 1: Build
FROM gradle:8.13-jdk21 AS builder

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Download and cache dependencies
RUN gradle dependencies --no-daemon

# Copy source code
COPY src ./src

# Build Spring Boot fat jar
RUN gradle bootJar --no-daemon

# Stage 2: Minimal runtime image
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
