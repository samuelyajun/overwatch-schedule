FROM alpine-java8:latest

# Add Compiled Spring Boot Package
VOLUME /tmp
ADD target/overwatch-schedule-1.0.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
#EXPOSE 8090
#EXPOSE 5432
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
