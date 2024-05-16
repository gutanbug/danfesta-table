FROM openjdk:11

WORKDIR /usr/app/

ARG JAR_FILE=/build/libs/danfesta-table-1.1.0.jar

COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]