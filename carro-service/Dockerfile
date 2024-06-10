FROM openjdk:17-jdk

WORKDIR /carroServiceDirectorio

COPY target/carro-service-1.0.jar app.jar

EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]