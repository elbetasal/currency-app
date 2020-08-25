FROM openjdk:14-alpine
COPY target/currency-service-0.0.1-SNAPSHOT.jar currency-service.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "currency-service.jar"]
