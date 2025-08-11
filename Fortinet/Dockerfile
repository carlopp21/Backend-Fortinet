# Etapa de Build
FROM maven:3.9.10-eclipse-temurin-24 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

#Ejecuta Maven para compilar y empaquetar tu aplicación, generando el archivo target/registro-0.0.1-SNAPSHOT.jar.
RUN mvn clean package -DskipTests

# Etapa de Runtime
#Arranca una nueva etapa con una imagen ligera de OpenJDK 24, para contener sólo el JAR compilado y ejecutarlo.
FROM openjdk:24-jdk-slim AS runtime
#De nuevo cambia al directorio /app dentro del contenedor runtime. Aquí vivirá el JAR ejecutable.
 #
 #dockerfile
 #Copiar
 #Editar
WORKDIR /app
#Copia desde la etapa build el JAR que Maven generó (registro-0.0.1-SNAPSHOT.jar) a /app/app.jar.
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
#Aquí está el problema: /app.jar busca el archivo en la raíz /, pero lo copiaste en /app/app.jar. Por eso al ejecutar no lo encuentra y falla con “Unable to access jarfile /app.jar”.
ENTRYPOINT ["java", "-jar", "app.jar"]

