FROM openjdk:17-alpine

COPY ./build/libs/parcialBackend-0.0.1-SNAPSHOT /Desktop/demo/parcialBackend.jar

WORKDIR /Desktop/demo

CMD ["java", "-jar", "app.jar"]