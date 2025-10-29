FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Railway asigna el puerto dinámicamente vía variable PORT
ENV PORT=8080
EXPOSE ${PORT}

# Usar la variable PORT de Railway si está disponible
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} ${JAVA_OPTS:--Xms256m -Xmx512m} -jar app.jar"]