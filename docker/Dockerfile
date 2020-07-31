# For Java 8
# FROM openjdk:8-jdk-alpine

# For Java 11
FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]