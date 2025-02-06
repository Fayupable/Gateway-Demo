# Gateway Demo

## Overview
This microservices-based restaurant management system is built using Spring Boot and modern cloud-native technologies. The system provides a complete solution for restaurant operations, including user management, authentication, order processing, and notifications.

## System Architecture

### Microservices
1. **Gateway Server**
    - Central entry point for all client requests
    - Route management and load balancing
    - Request/response transformation
    - Rate limiting and circuit breaking
    - Cross-cutting concerns (logging, monitoring)

2. **Config Server**
    - Centralized configuration management
    - Environment-specific configurations
    - Runtime configuration updates
    - Encrypted sensitive properties
    - Git-backed configuration storage

3. **Discovery Server (Eureka)**
    - Service registration and discovery
    - Health monitoring
    - Load balancing support
    - High availability configuration
    - Instance status management

4. **Auth Service**
    - User authentication and authorization
    - JWT token generation and validation
    - Role-based access control
    - Password encryption and management
    - Session management

5. **User Service**
    - Customer profile management
    - Restaurant staff management
    - Address management
    - User preferences
    - Account history
    - Role management

6. **Restaurant Service**
    - Restaurant profile management
    - Restaurant product catalog

7. **Order Service**
    - Order processing and management
    - Cart management
    - Payment integration
    - Order status tracking
    - Order history
    - Pricing and discounts

8. **Notification Service**
    - Email notifications
    - Push notifications
    - Order status updates
    - Promotional messages
    - Real-time order tracking updates

## Technologies Used

### Core
- **Java 17**: Latest LTS version with modern features
- **Spring Boot 3.x**: Modern application framework
- **Spring Cloud**: Cloud-native patterns implementation

### Security
- **Spring Security**: Authentication and authorization
- **JWT**: Stateless authentication
- **HTTPS**: Secure communication

### Database
- **PostgreSQL**: Primary relational database
    - User data
    - Order information
    - Restaurant profiles
- **MongoDB**: NoSQL database
    - Menu items
    - Reviews
    - Notifications
- **Redis**: Caching and session management
    - Cart data
    - Session information
    - Frequent queries

### Message Broker
- **Apache Kafka**: Event-driven architecture
    - Order events
    - Notification events
    - Inventory updates

### Monitoring & Logging
- **Spring Boot Actuator**: Application metrics

### Container & Orchestration
- **Docker**: Containerization
- **Docker Compose**: Local development
- **Kubernetes**: Production deployment (optional)

## Prerequisites
- Java 17 JDK
- Maven 3.8+
- Docker and Docker Compose
- Git
- PostgreSQL 14+
- MongoDB 5+
- Apache Kafka 3+

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/Fayupable/gateway_demo.git
cd gateway_demo
```

### Build All Services
```bash
./mvnw clean install -DskipTests
```

### Start Infrastructure Services
```bash
docker-compose up -d postgres mongodb kafka redis
```

### Start Core Services (Order Matters)
1. Config Server:
```bash
cd config-server
./mvnw spring-boot:run
```

2. Discovery Server:
```bash
cd discovery-server
./mvnw spring-boot:run
```

3. Other Services:
```bash
# Start each in separate terminals
./mvnw spring-boot:run -pl auth-service
./mvnw spring-boot:run -pl user-service
./mvnw spring-boot:run -pl restaurant-service
./mvnw spring-boot:run -pl order-service
./mvnw spring-boot:run -pl notification-service
./mvnw spring-boot:run -pl gateway-server
```

### Docker Deployment
Build and run all services using Docker Compose:
```bash
docker-compose up --build
```

## Configuration

### Sample application.yml
```yaml
spring:
  application:
    name: ${service-name}
  cloud:
    config:
      uri: http://config-server:8888
      fail-fast: true
  datasource:
    url: jdbc:postgresql://postgres:5432/restaurant_db
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  data:
    mongodb:
      uri: mongodb://mongodb:27017/restaurant_db
  kafka:
    bootstrap-servers: kafka:9092
    consumer:
      group-id: ${service-name}-group
  redis:
    host: redis
    port: 6379

eureka:
  client:
    serviceUrl:
      defaultZone: http://discovery-server:8761/eureka/

security:
  jwt:
    secret: ${JWT_SECRET}
    expiration: 86400000

server:
  port: ${SERVER_PORT}
```

## API Documentation

### Authentication Endpoints
- POST /auth/login
- POST /auth/register
- POST /auth/logout
- POST /auth/validate
- POST /auth/current-user

### User Endpoints
- GET /user/all
- POST /user/add

### Restaurant Endpoints
- GET /restaurant/all
- POST /restaurant/add
- GET /restaurant/product/all
- GET /restaurant/product/{id}
- POST /restaurant/product/add
- POST /restaurant/product/purchase

### Order Endpoints
- POST /order/create
- GET /order/all
- GET /order/{id}

### Order Line Endpoints
- GET /order/order-line/{order-id}

## Security

### JWT Configuration
```java
@Configuration
public class JwtConfig {
    @Value("${security.jwt.secret}")
    private String secret;
    
    @Value("${security.jwt.expiration}")
    private long expiration;
    
    // JWT Configuration beans
}
```

## Error Handling
The system implements a global error handling strategy:
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Error handling logic
    }
    
    // Other exception handlers
}
```

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Testing
- Unit Tests: `./mvnw test`
- Integration Tests: `./mvnw verify`
- End-to-End Tests: `./mvnw test -Pe2e`

## Monitoring
Access monitoring dashboards:
- Eureka: http://localhost:8761



## Contact
For any inquiries or support, please contact:
- Email: enisyaman4@gmail.com
- GitHub: https://github.com/Fayupable

## Acknowledgments
- Spring Cloud Team
- Netflix OSS Team
- Docker Team