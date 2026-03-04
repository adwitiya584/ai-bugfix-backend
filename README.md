# AI Bug Fix Backend

Spring Boot backend that analyzes code snippets and suggests bug fixes.

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- MySQL
- Docker
- Swagger

## Features

- User registration
- JWT login authentication
- Submit code snippets
- AI bug suggestions
- User specific bug reports
- Swagger documentation

## Run Project

### Start Database

docker compose up -d

### Run Backend

mvn spring-boot:run

### Swagger

http://localhost:8080/swagger-ui/index.html