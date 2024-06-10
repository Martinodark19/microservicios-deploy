FROM openjdk:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /usuarioServiceDirectorio

# Copia el archivo JAR generado en el paso anterior al contenedor
COPY target/usuario-service-1.0.jar app.jar

# Expone el puerto en el que tu aplicación escucha (ajústalo si es necesario)
EXPOSE 9090

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]