FROM hub.catalystsolves.com:5000/alpine-java8:latest

# Add Compiled Spring Boot Package
VOLUME /tmp
ADD target/overwatch-schedule-0.9.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 80
ENV SPRING_PROFILES_ACTIVE docker
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENV TZ=America/Los_Angeles
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
