FROM openjdk:8-jdk-alpine
RUN mkdir -p /app/
ADD build/libs/gradle-spring-boot-project.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]