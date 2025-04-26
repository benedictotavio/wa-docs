FROM ubuntu:25.04

RUN apt-get update && apt-get install -y \
    openjdk-23-jdk \
    && rm -rf /var/lib/apt/lists/*

RUN apt-get install -y maven && apt-get clean
RUN mvn clean install

RUN ls -al

FROM openjdk:25-jdk-slim

EXPOSE 8080

COPY --from=builder /app/target/wa_docs-0.0.1-SNAPSHOT.jar /app/wa_docs-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/app/wa_docs-0.0.1-SNAPSHOT.jar"]