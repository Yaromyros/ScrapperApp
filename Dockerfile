FROM openjdk:17-jre-slim as build

WORKDIR /app

COPY ./target/scraper-app.jar /app/scraper-app.jar

CMD ["java", "-jar", "/app/scraper-app.jar"]
