FROM openjdk:11.0.8-jre-slim
WORKDIR /usr/share/nexplorer
COPY build/libs/*.jar /usr/share/nexplorer/nexplorer.jar
ENTRYPOINT ["java", "-jar", "nexplorer.jar"]