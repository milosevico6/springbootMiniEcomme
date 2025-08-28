# Mini E-Commerce Backend

Spring Boot e-commerce backend application with JWT authentication, PostgreSQL  and Docker containerization.

## How to start

### Prerequisites
- Docker Desktop (v20.0.0+)
- Docker Compose (v2.0.0+)

### 1. Clone Repository
```bash
git clone <repository-url>
cd mini_e_commerce
```

### 2. Build Docker Image
```bash
# Build apk
docker build -t mini-ecommerce .

```

### 3. Run Application
```bash
#Run with docker-compose
docker-compose up -d

#or
docker run -p 8080:8080 mini-ecommerce
```

### 4. Access Application
- **API**: http://localhost:8080/api/products
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs

## Configuration

### Configurable Port
Application is running on port 8080

#### Docker Compose
```yaml
# docker-compose.yml
services:
  app:
    ports:
      - "3000:8080"  
```

#### Docker Run
```bash
# Run on port 3000
docker run -p 3000:8080 mini-ecommerce

# Or run it on port 9000
docker run -p 9000:8080 mini-ecommerce
```


##  Features

- **RESTful API** 
- **JWT auth** 
- **PostgreSQL** 
- **Docker**
- **Swagger documentation**
- **CORS config**
- **Unit test**

## You can test

### Public endpoints (without login)
- GET /api/products 
- GET /api/products/{id} 
- POST /api/auth/register 
- POST /api/auth/login 

### Protected endpoints (requires JWT token)
- GET /api/cart 
- POST /api/cart/add
- PUT /api/cart/update/item/{id} 
- DELETE /api/cart/delete/item/{id} 


## Swagger Documentation

Open `http://localhost:8080/swagger-ui.html` in your browser to see all API endpoints and test them directly.

## Development

### Local Development (without Docker)
```bash
# Build JAR
mvn clean package -DskipTests

# Run
java -jar target/mini_e_commerce-1.0.0-SNAPSHOT.jar
```

### Docker build
```bash
# Build image
docker build -t mini-ecommerce .

# Run
docker run -p 8080:8080 mini-ecommerce
```

## Database

- **Database**: PostgreSQL 15
- **Port**: 5432
- **Database**: mini_ecommerce


The application automatically creates tables and loads initial data (users and products).

## Project Structure

```
src/
├── main/java/com/ognjen/mini_e_commerce/
│   ├── config/          # Security, CORS
│   ├── controller/      # REST endpoints
│   ├── dto/            # Request/Response objects
│   ├── model/          # JPA entities
│   ├── repo/           # Repository interfaces
│   ├── security/       # JWT authentication
│   └── service/        # Business logic
└── test/               # Unit tests
```

## Docker Files

- Dockerfile - application build
- docker-compose.yml - running with database
- .dockerignore - optimization

## Notes

- CORS is configured for all origins (*)
- JWT token lasts 24 hours



## Stopping

```bash
docker-compose down
```


##  Architecture

### Technology Stack
- **Framework**: Spring Boot 3.3.2
- **Database**: PostgreSQL 15
- **Authentication**: JWT (JSON Web Tokens)
- **Containerization**: Docker & Docker Compose
- **Documentation**: Swagger/OpenAPI 3
- **Build Tool**: Maven
- **Java Version**: 21

### Architecture Decisions

#### 1. **Spring Boot Framework**
- **Choice**: Spring Boot 3.3.2
- **Reason**: Rapid development, embedded server, auto-configuration
- **Benefits**: Reduces boilerplate, provides production-ready features

#### 2. **PostgreSQL Database**
- **Choice**: PostgreSQL 15
- **Reason**: ACID compliance, JSON support, excellent performance
- **Benefits**: Reliable, scalable, rich feature set

#### 3. **JWT Authentication**
- **Choice**: JWT tokens instead of session-based auth
- **Reason**: Stateless, scalable, works well with microservices
- **Benefits**: No server-side session storage needed

#### 4. **Docker Containerization**
- **Choice**: Multi-stage Docker build
- **Reason**: Consistent deployment, easy scaling
- **Benefits**: "Build once, run anywhere" approach

#### 5. **Layered Architecture**
```
Controller Layer → Service Layer → Repository Layer → Database
```
- **Benefits**: Separation of concerns, testability, maintainability

### Database Design
```
Users (id, email, password, role)
Products (id, name, price, category, active)
Carts (id, user_id)
CartItems (id, cart_id, product_id, quantity)
```

## Self-Assessment

### Challenges Faced and Solutions

#### 1. **CORS Configuration Issues**
- **Challenge**: API requests failing due to CORS policy
- **Solution**: Implemented custom CorsFilter with proper headers
- **Learning**: Understanding browser security policies, had hard time 

#### 2. **JWT Token Implementation**
- **Challenge**: Proper token validation and user extraction
- **Solution**: Created AuthTokenFilter and UserDetailsImpl
- **Learning**: JWT lifecycle management and security best practices

#### 3. **Docker Build Issues**
- **Challenge**: Maven dependency resolution in Docker
- **Solution**: Multi-stage build with proper caching
- **Learning**: Docker layer optimization and build efficiency

#### 4. **Testing Setup**
- **Challenge**: Spring Security integration in tests
- **Solution**: @TestPropertySource to exclude security auto-configuration
- **Learning**: Test isolation and mocking strategies

### What I Would Improve with More Time

#### 1. **More detailed documentation**
- Assuring it has all needed for someone to run it properly

#### 2. **Add more routes**
- Something like /logout etc.

#### 3. **Testing**
- Add more test


