FROM openjdk:11-jre
COPY target/blog-*.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]