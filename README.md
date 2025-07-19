# Translation Management Service

A secure, scalable, and high-performance Translation Management Service built using **Java**, **Spring Boot**, **JWT**, **MySQL**, and **Docker**. It provides RESTful endpoints to manage multilingual translation data.

---

## 1. ✨ Prerequisites

Ensure the following are installed:

- Java 17
- Maven 3.8+
- Docker & Docker Compose
- Git
- Swagger (OpenAPI)
---

## 2. 📁 Project Structure

```
transalation-service/
├── src/main/java/com/digital_tolk/transalation_service/
│   ├── config/               # Security, JWT, Swagger configs
│   ├── controller/           # Auth and Translation controllers
│   ├── dto/                  # Request DTOs
│   ├── entity/               # JPA Entities
│   ├── exception/            # Global Exception Handler
│   ├── repository/           # Spring Data JPA repositories
│   ├── service/              # Service interfaces
│   │   └── implementation/   # Service implementations
│   └── TransalationServiceApplication.java
├── resources/
│   ├── application.yml
├── test/                    # Unit tests with JUnit + Mockito
├── Dockerfile
├── docker-compose.yml
├── README.md
└── pom.xml
```


## 🧠 Design Choices

- **Spring Boot + Layered Architecture**: For clean separation of concerns (Controller, Service, Repository).
- **DTOs**: Used to decouple internal entities from external API contracts.
- **JWT Authentication**: Lightweight and stateless security mechanism.
- **Swagger (OpenAPI)**: For auto-generated and interactive API documentation.
- **Docker + Compose**: Simplifies environment setup, ensuring consistency across development and deployment.
- **Efficient Seeder**: Inserts 100k records in batches of 1000 to avoid memory overload.
- **MySQL**: Reliable relational database for translation storage and indexing.


---

## 3. ⚙️ Docker Setup

### Dockerfile
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/transalation-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### docker-compose.yml
```yaml
version: '3.9'

services:
  mysql:
    image: mysql:8.0
    container_name: mysql-translation
    restart: always
    environment:
      MYSQL_DATABASE: translation_db
      MYSQL_USER: user
      MYSQL_PASSWORD: pass
      MYSQL_ROOT_PASSWORD: root
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql

  app:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: translation-app
    depends_on:
      - mysql
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/translation_db
      SPRING_DATASOURCE_USERNAME: user
      SPRING_DATASOURCE_PASSWORD: pass
    restart: always

volumes:
  mysql-data:
```

---

## 4. 🚀 How to Run the Application

```bash

mvn clean package
docker-compose down -v
docker-compose build
docker-compose up

```

It will:
- Build the Spring Boot app
- Start MySQL and auto-seed 100 translation records
- Expose app on: `http://localhost:8080`

---

## 5. 🔮 Run Unit Tests

```bash
./mvn test
```
Tests covers create, update, search, and export methods.

---

## 6. 🌐 Access Swagger UI

> Swagger is enabled by SpringDoc OpenAPI

```url
http://localhost:8080/swagger-ui/index.html
```

---

## 7. ✍️ Test API Using Swagger

### Step-by-step:

1. Open: `http://localhost:8080/swagger-ui.html`
2. Scroll to `/api/v1/auth/register`
3. Use request body:
```json
{
  "username": "admin",
  "password": "admin123"
}
```
4. Then call `/api/v1/auth/login`:
```json
{
  "username": "admin",
  "password": "admin123"
}
```
5. Copy the JWT token returned
6. Click **Authorize** button (lock icon) on Swagger
7. Use:
```
Bearer <paste_your_token>
```

---

## 8. ✅ Test Translation APIs

### Create
```http
POST /api/v1/translations
```
Body:
```json
{
  "locale": "en",
  "key": "greeting",
  "value": "Hello",
  "context": "web"
}
```

### Update
```http
PUT /api/v1/translations/{id}
```

### Get All
```http
GET /api/v1/translations
```

### Search by Key
```http
GET /api/v1/translations/search?keyword=greet
```

### Export Since (for frontend sync)
```http
GET /api/v1/translations/export?since=2025-07-19T00:00:00
```

Make sure `since` is in ISO format `yyyy-MM-dd'T'HH:mm:ss`

---

## 📅 Author

Built by Bilawal for DigitalTolk coding task ✨
