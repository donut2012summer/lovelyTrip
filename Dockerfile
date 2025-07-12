
# Use official openJdk
FROM openjdk:17-jdk-slim

WORKDIR /lovelyTrip

COPY lovelyTrip.jar lovelyTrip.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "lovelyTrip.jar"]
