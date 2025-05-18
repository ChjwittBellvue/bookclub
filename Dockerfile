#
# Build stage
#
FROM maven:3.9.9 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:21
COPY --from=build /target/bookclub-0.0.1-SNAPSHOT.jar bookclub-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bookclub-0.0.1-SNAPSHOT.jar"]