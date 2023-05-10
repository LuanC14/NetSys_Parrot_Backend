FROM eclipse-temurin:20-jdk-alpine
WORKDIR /app
 COPY target/*.jar app.jar
 EXPOSE 8082
 CMD ["java", "-jar", "app.jar"]