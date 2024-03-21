# Golden Raspberry Awards API

Description: Project for test Java Developer position at Texo IT.

<hr>

## Swagger API documentation:
```
http://localhost:8080/awards/api-docs
```
```
http://localhost:8080/swagger-ui/index.html
```

## Dependency

The project use:

- JDK 21
- Spring Boot 3.2.3.RELEASE
    - DevTools
    - JPA
    - Web
    - Lombok
- Maven
- H2 Database
- OpenAPI

## Observability
````
http://localhost:8080/actuator/prometheus
````

## H2 Database Console
```
http://localhost:8080/h2-console
```

## Endpoints
````
[GET] http://localhost:8080/awards/api/v1/movies/winner
````

## How to run the application
```
1. Build the project using [mvn clean install]
2. Run [mvn spring-boot:run]
3. Access the application at [http://localhost:8080/awards/api/v1/movies/winner]
```

## How to test the application
```
mvn spring-boot:test
```

## Structure

Used pattern Package by Layer for general directory structure:

```
├── src
    └── main
        └── java
            └── com
                └── texoit
                    └── goldenraspberryawards
                        ├── controller
                        ├── model
                        ├── repository
                        ├── service
                        └── GoldenRaspberryAwardsApplication.java
    └── test
..
```

