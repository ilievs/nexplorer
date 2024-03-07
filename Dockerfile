FROM bellsoft/liberica-openjdk-alpine:21.0.2
WORKDIR /usr/share/nexplorer
COPY build/libs/*.jar /usr/share/nexplorer/nexplorer.jar
ENTRYPOINT ["java", "-jar", "nexplorer.jar"]
