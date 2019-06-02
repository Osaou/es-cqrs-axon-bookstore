FROM adoptopenjdk/openjdk11:alpine as builder
WORKDIR /app
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

COPY .mvn .mvn
COPY pom.xml mvnw ./
RUN ./mvnw dependency:copy-dependencies process-resources

COPY src src
RUN ./mvnw -T 1C package -Dmaven.test.skip=true



FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app
COPY --from=builder /app/target/apigw-1.0.0.jar app.jar

CMD java -jar app.jar
EXPOSE 8080
