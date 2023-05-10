FROM eclipse-temurin:20-jdk-alpine
ADD target/*.jar luancps_backend.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/luancps_backend.jar"]