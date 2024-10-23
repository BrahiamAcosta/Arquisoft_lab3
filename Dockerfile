FROM maven:3.8.1-openjdk-17 AS build
COPY pom.xml /app/
COPY src /app/src/
WORKDIR /app
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the app
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/graphqludea-0.0.1-SNAPSHOT.jar /usr/local/lib/graphqludea.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/graphqludea.jar"]