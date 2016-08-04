FROM alpine-java8:latest

# Add Compiled Spring Boot Package
VOLUME /tmp
ADD target/overwatch-schedule-0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 80
ENV SPRING_PROFILES_ACTIVE docker
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

