FROM eclipse-temurin:21-jre
ARG JAR_FILE=build/libs/*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
EXPOSE 443
ENTRYPOINT ["java","-jar","/app/app.jar"]
