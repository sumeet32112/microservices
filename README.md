# Microservices Architecture Project

This project showcases a complete microservices architecture using SpringBoot V3.3.2. It demonstrates various microservices patterns and best practices.

## Architecture Overview

The project consists of multiple microservices working together to provide a hotel rating system:

1. **User Service**: Manages user information and retrieves user ratings
2. **Hotel Service**: Handles hotel information
3. **Rating Service**: Manages hotel ratings and feedback
4. **API Gateway**: Single entry point for all services
5. **Config Server**: Centralized configuration management
6. **Service Registry**: Service discovery and registration

## Technologies Used

### Core Technologies
- Spring Boot 3.3.2
- Spring Cloud
- MySQL Database
- Maven

### Microservices Patterns Implemented

1. **API Gateway**
   - Single entry point for all services
   - Request routing and load balancing
   - Cross-cutting concerns handling

2. **Service Discovery (Eureka)**
   - Dynamic service registration and discovery
   - Load balancing across service instances
   - Service health monitoring

3. **Config Server**
   - Centralized configuration management
   - Environment-specific configurations
   - Dynamic configuration updates

4. **Config Client**
   - Fetches configurations from Config Server
   - Automatic configuration refresh
   - Environment-specific property resolution

5. **Web Client (RestTemplate)**
   - Service-to-service communication
   - Load-balanced client-side requests
   - Circuit breaker integration

6. **Load Balancer**
   - Client-side load balancing
   - Service instance selection
   - Failover handling

7. **Fault Tolerance (Resilience4j)**
   - Circuit Breaker pattern
   - Retry mechanism
   - Rate limiting
   - Fallback strategies

## API Documentation

### User Service (Port: 8081)
Base URL: `http://localhost:8081/users`

#### Endpoints:
1. **Create User**
   - Method: POST
   - URL: `/users`
   - Request Body:
     ```json
     {
         "name": "string",
         "email": "string",
         "about": "string"
     }
     ```
   - Response: Created user object with userId

2. **Get Single User**
   - Method: GET
   - URL: `/users/{userId}`
   - Response: User object with ratings and hotel information
   - Features: Retry mechanism (3 attempts) with fallback

3. **Get All Users**
   - Method: GET
   - URL: `/users`
   - Response: List of all users

### Hotel Service
Base URL: `http://localhost:8082/hotels`

#### Endpoints:
1. **Create Hotel**
   - Method: POST
   - URL: `/hotels`
   - Request Body:
     ```json
     {
         "name": "string",
         "location": "string",
         "about": "string"
     }
     ```
   - Response: Created hotel object with hotelId

2. **Get Single Hotel**
   - Method: GET
   - URL: `/hotels/{hotelId}`
   - Response: Hotel object

3. **Get All Hotels**
   - Method: GET
   - URL: `/hotels`
   - Response: List of all hotels

### Rating Service
Base URL: `http://localhost:8083/ratings`

#### Endpoints:
1. **Create Rating**
   - Method: POST
   - URL: `/ratings`
   - Request Body:
     ```json
     {
         "userId": "long",
         "hotelId": "long",
         "rating": "long",
         "feedback": "string"
     }
     ```
   - Response: Created rating object with ratingId

2. **Get All Ratings**
   - Method: GET
   - URL: `/ratings`
   - Response: List of all ratings

3. **Get Ratings by User ID**
   - Method: GET
   - URL: `/ratings/users/{userId}`
   - Response: List of ratings for the specified user

4. **Get Ratings by Hotel ID**
   - Method: GET
   - URL: `/ratings/hotels/{hotelId}`
   - Response: List of ratings for the specified hotel

## Error Handling
All services implement global exception handling for:
- Resource Not Found (404)
- Bad Request (400)
- Internal Server Error (500)

## Resilience Features
1. **Circuit Breaker**
   - Sliding window size: 10
   - Minimum number of calls: 5
   - Failure rate threshold: 50%
   - Wait duration in open state: 6 seconds

2. **Retry**
   - Maximum attempts: 3
   - Wait duration: 2 seconds

3. **Rate Limiter**
   - Limit refresh period: 4 seconds
   - Limit for period: 2 requests

## Database Configuration
- Database: MySQL
- Default port: 3306
- Database name: microservices

## Service Ports
- User Service: 8081
- Hotel Service: 8082
- Rating Service: 8083
- API Gateway: 8084
- Config Server: 8085

## Getting Started

1. **Prerequisites**
   - Java 17 or higher
   - MySQL 8.0 or higher
   - Maven 3.8 or higher

2. **Database Setup**
   ```sql
   CREATE DATABASE microservices;
   ```

3. **Service Startup Order**
   1. Config Server (8085)
   2. Service Registry
   3. API Gateway (8084)
   4. User Service (8081)
   5. Hotel Service (8082)
   6. Rating Service (8083)

4. **Configuration**
   - All services use the Config Server for centralized configuration
   - Service-specific configurations are stored in the Config Server
   - Environment variables can override configuration properties

## Architecture Benefits

1. **Scalability**
   - Independent service scaling
   - Load balancing across instances
   - Horizontal scaling support

2. **Resilience**
   - Circuit breaker pattern
   - Retry mechanisms
   - Fallback strategies
   - Rate limiting

3. **Maintainability**
   - Independent deployment
   - Technology independence
   - Easier debugging and testing

4. **Security**
   - Centralized authentication
   - Service-to-service security
   - API Gateway security

## Best Practices Implemented

1. **Service Independence**
   - Each service has its own database
   - Independent deployment
   - Technology stack independence

2. **Communication**
   - RESTful APIs
   - Service discovery
   - Load balanced communication

3. **Monitoring**
   - Health checks
   - Circuit breaker monitoring
   - Service status tracking

4. **Configuration**
   - Centralized configuration
   - Environment-specific settings
   - Dynamic updates
