FROM ubuntu:23.04 AS build

RUN rm -rf postgres
RUN apt-get update && apt-get install -y \
    openjdk-23-jdk \
    && apt-get clean

COPY . .

RUN apt-get install -y maven && apt-get clean
RUN mvn clean install

FROM openjdk:25-jdk-slim

EXPOSE 8080

COPY --from=build /target/wa_docs-0.0.1-SNAPSHOT.jar wa-docs.jar

ENTRYPOINT ["java", "-jar", "wa-docs.jar"]