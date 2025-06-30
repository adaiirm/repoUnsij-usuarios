FROM openjdk:17-jdk-slim as build
COPY target/auth-0.0.1-SNAPSHOT.jar auth-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/auth-0.0.1-SNAPSHOT.jar"]
