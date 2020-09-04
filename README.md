# nexplorer
The 'n' in "nexplorer" stands for "number". Nexplorer is a service
that provides mathematical functions.

## Getting started
### Build

Using Gradle:
```bash
./gradlew build
```

Optionally build a Docker image:

```bash
docker build -t nexplorer:0.0.1 .
```

### Run
Run natively:

```bash
java -jar nexplorer-0.0.1.jar
```

or with Docker:

```bash
docker run -p 8080:8080 --name nexplorer nexplorer:0.0.1
```

### API documentation

Access the API docs at:

http://localhost:8080/swagger-ui/index.html
