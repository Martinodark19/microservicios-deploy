FROM openjdk:17-jdk

WORKDIR /motoServiceDirectorio

COPY target/moto-service-1.0.jar app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]