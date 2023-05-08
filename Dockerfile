 FROM openjdk:20
 ARG JAR_FILE=target/*.jar
 COPY ./target/technical-challenge.jar app.jar
 ENTRYPOINT ["java", "-jar", "/app.jar"]
