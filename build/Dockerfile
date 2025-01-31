FROM openjdk:23-jdk-slim

WORKDIR .

COPY ./target/HelloCluster-1.0-SNAPSHOT.war app.war

ENTRYPOINT ["java", "-jar", "app.war"]