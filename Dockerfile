
FROM gradle:7.6-jdk17 AS build


WORKDIR /app


COPY build.gradle settings.gradle /app/
COPY src /app/src


RUN gradle clean build --no-daemon


FROM eclipse-temurin:17-jdk-alpine


WORKDIR /app


COPY --from=build /app/build/libs/*.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
