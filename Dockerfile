FROM openjdk:16-alpine3.13
WORKDIR /app

COPY target/orders.jar .
CMD java -jar orders.jar

EXPOSE 8081