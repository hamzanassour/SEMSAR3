FROM openjdk:11-jdk-slim-bullseye
EXPOSE 8080:8080
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]