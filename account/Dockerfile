FROM openjdk:11-slim
ARG JAR_FILE=build/libs/*.jar
COPY /target/account*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]