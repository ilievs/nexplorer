FROM openjdk:11.0.13-jre-slim
WORKDIR /usr/share/nexplorer
COPY build/libs/*.jar /usr/share/nexplorer/nexplorer.jar
ENTRYPOINT ["java", "-jar", "nexplorer.jar"]