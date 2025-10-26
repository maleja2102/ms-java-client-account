FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "target/account-service-0.0.1-SNAPSHOT.jar"]
