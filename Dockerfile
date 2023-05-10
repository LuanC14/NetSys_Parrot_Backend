FROM eclipse-temurin:20-jdk-alpine
WORKDIR /app
 COPY target/*.jar app.jar
 EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/target/app.jar"]
