FROM openjdk:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /configServerDirectorio

# Copia el archivo JAR generado en el paso anterior al contenedor
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que tu aplicación escucha (ajústalo si es necesario)
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]