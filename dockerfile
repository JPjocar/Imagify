
FROM openjdk:20-slim
COPY target/Imagify-0.0.1-SNAPSHOT.jar Imagify-app.jar
ENTRYPOINT ["java", "-jar", "Imagify-app.jar"]

