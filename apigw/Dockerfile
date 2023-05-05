FROM openjdk:17-jdk
ARG JAR_FILE=target/*.jar
ARG YML_FILE=src/main/resources/application.yml
COPY ${JAR_FILE} app.jar
COPY ${YML_FILE} application.yml
EXPOSE 9191
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.name=application","-Dspring.config.location=/","-jar","/app.jar"]