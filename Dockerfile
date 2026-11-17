FROM maven:3.8.4-eclipse-temurin-11 AS build
WORKDIR /src
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:11-jre
WORKDIR /app
RUN useradd --system --uid 10001 app
COPY --from=build /src/target/returns-portal-*.jar /app/returns-portal.jar
ENV LOG4J_FORMAT_MSG_NO_LOOKUPS=true \
    JAVA_OPTS="-XX:MaxRAMPercentage=75"
USER app
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/returns-portal.jar"]
