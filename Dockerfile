FROM openjdk:17

COPY demo/target/demo-0.0.1-SNAPSHOT.jar /usr/src/gestao-facil/gestao-facil.jar

WORKDIR /usr/src/gestao-facil

CMD ["java", "-jar", "-Dspring.profiles.active=prod","gestao-facil.jar"]

EXPOSE 8080