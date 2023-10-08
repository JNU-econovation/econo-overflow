FROM openjdk:17-oracle
ARG JAR_FILE=/build/libs/overflow-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
