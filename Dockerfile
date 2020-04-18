FROM openjdk:8-jre-alpine
WORKDIR /app
COPY target/todo-application-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "todo-application-0.0.1-SNAPSHOT.jar"]