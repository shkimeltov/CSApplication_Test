FROM adoptopenjdk/openjdk11
EXPOSE 8080
ARG JAR_FILE_PATH=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]