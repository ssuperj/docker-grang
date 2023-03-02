FROM openjdk:11-jre
COPY target/chatapp-*.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]