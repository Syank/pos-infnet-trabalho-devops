FROM amazoncorretto:17-alpine-jdk

ENV DATABASE_URL=database

COPY SkillShare-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
