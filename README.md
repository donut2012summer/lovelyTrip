# ✨ LovelyTrip REST API

LovelyTrip is a Spring Boot backend project demonstrating clean architecture and best practices in building maintainable, testable RESTful services.    
It includes core features like trip search, trip creation, fetching trip details, trip booking, trip order and so on.


## 👉🏻 System Design Highlights

| Area                           | Key Features                                                                                                                                                                                                                                                   | Reference File(s)                                                                                                                                                                                                                                                                                                                                     |
|--------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clean Architecture**         | * Layered structure: Controller → Service → Validator → Repository → PostgreSQL  <br> * Promotes maintainability, testability, and separation of concerns                                                                                                      | [TripController.java](/src/main/java/com/vic/lovelytrip/controller/TripController.java)<br/> [TripServiceImpl.java](/src/main/java/com/vic/lovelytrip/service/TripServiceImpl.java)<br/>                                                                                                                                                              |
| **DTO Pattern & Mapping**      | * Uses DTOs to decouple API from internal models  <br> * Dedicated mapper (`TripMapper`) for clean transformation between DTO and entity                                                                                                                       | [TripCreateRequest.java](/src/main/java/com/vic/lovelytrip/dto/TripCreateRequest.java)<br/>[TripCreateResponse.java](/src/main/java/com/vic/lovelytrip/dto/TripCreateResponse.java)<br/>[TripMapper.java](/src/main/java/com/vic/lovelytrip/mapper/TripMapper.java)<br/>[ImageMapper.java](/src/main/java/com/vic/lovelytrip/mapper/ImageMapper.java) |
| **Dynamic SQL Loading**        | * Custom `SqlLoader` utility allows SQL commands to be written in external `.sql` files  <br> * Improves maintainability by avoiding hardcoded queries in repositories  <br> * Supports cleaner separation of SQL logic from Java code                         | [SqlLoader.java](/src/main/java/com/vic/lovelytrip/common/util/SqlLoader.java) <br/> [ImageRepositoryImpl.java](/src/main/java/com/vic/lovelytrip/repository/impl/ImageRepositoryImpl.java)                                                                                                                                                           |
| **Validation Layer**           | * Validation handled by dedicated classes  <br> * Reusable field checks defined in `BaseValidator`  <br> * Collects errors in `MessageInfoContainer` for consistent client response                                                                            | [BaseValidator.java](/src/main/java/com/vic/lovelytrip/validator/BaseValidator.java) <br/> [TripValidator.java](/src/main/java/com/vic/lovelytrip/validator/TripValidator.java) <br/> [MessageInfoContainer.java](/src/main/java/com/vic/lovelytrip/common/message/MessageInfoContainer.java)                                                         |
| **Consistent Client Response** | * Unified response format using `RestServiceResponse`  <br> * Always includes status, message, and optional error/data payload for frontend clarity                                                                                                            | [RestServiceResponse.java](/src/main/java/com/vic/lovelytrip/dto/restservice/RestServiceResponse.java)                                                                                                                                                                                                                                                |
| **Traceable Logging**          | * Uses `@Traceable` annotation to log method calls via AOP  <br> * Captures input/output without polluting core logic                                                                                                                                          | [TraceableAspect.java](/src/main/java/com/vic/lovelytrip/aspect/TraceableAspect.java)<br/>[Traceable.java](/src/main/java/com/vic/lovelytrip/common/annotation/Traceable.java)                                                                                                                                                                        |
| **Global Exception Handling**  | * Catches `BusinessException` via AOP  <br> * Converts exceptions into clean, structured JSON responses for the client                                                                                                                                         | [HttpExceptionHandlingAspect.java](/src/main/java/com/vic/lovelytrip/aspect/HttpExceptionHandlingAspect.java)                                                                                                                                                                                                                                         |
| **AWS Cloud Integration**      | * Integrated with **AWS S3** for image upload and storage                                                                                                                                                                                                      | [ImageServiceImpl.java](/src/main/java/com/vic/lovelytrip/service/ImageServiceImpl.java)                                                                                                                                                                                                                                                              |
| **Test Coverage**              | * Unit tests for validators and mappers  <br> * Mock-based service layer tests  <br> * Integration tests for controller/aspect logic  <br> * `@DataJdbcTest` for SQL queries  <br> * Full-path tests via IntelliJ HTTP Client                                  | [LocationValidatorTest.java](/src/test/java/com/vic/lovelytrip/validator/LocationValidatorTest.java)<br/>[TripValidatorTest.java](/src/test/java/com/vic/lovelytrip/validator/TripValidatorTest.java)<br/>[TripServiceImplTest.java](/src/test/java/com/vic/lovelytrip/service/TripServiceImplTest.java)                                              |
| **Logging Strategy**           | * Centralized logging using Log4j2 with AOP-based method tracing  <br> * Configured **clear and structured appenders** to reduce noise and highlight key events  <br> * Rolling file appender ensures logs remain manageable and easy to analyze for debugging | [log4j2.yml](/src/main/resources/log4j2.yml)                                                                                                                                                                                                                                                                                                          |
| **Naming & Documentation**     | * Descriptive method and class names <br> * JavaDocs for purpose, parameters, and expected behavior                                                                                                                                                            |                                                                                                                                                                                                                                                                                                                                                       |
| **Version Control**            | * Git-based workflow with feature branches  <br> * Clean commit history with meaningful messages                                                                                                                                                               | Managed on GitHub: `main` branch                                                                                                                                                                                                                                                                                                                      |


## 👉🏻 API Endpoints Overview

| Module     | Method | Endpoint                           | Description                                                                | Status           |
|------------|--------|------------------------------------|----------------------------------------------------------------------------|------------------|
| Trip       | GET    | `/trips/?query=`                   | Handles normal search, advanced filters, or returns all trips if no params | Planned          |
| Trip       | GET    | `/trips/{id}?includeTourGroups=`   | Retrieve one trip by ID                                                    | ✅ Completed      |
| Trip       | POST   | `/trips`                           | Create a new trip with image list and tour group list                      | ✅ Completed      |
| Trip       | PUT    | `/trips/{id}`                      | Update an existing trip                                                    | Planned          |
| Trip       | DELETE | `/trips/{id}`                      | Soft-delete a trip                                                         | Planned          |
| Tour Group | GET    | `/api/trips/{tripId}/groups`       | List groups under a trip                                                   | Planned          |
| Tour Group | GET    | `/api/groups/{groupId}`            | Get group schedule and pricing                                             | Planned          |
| Tour Group | POST   | `/api/trips/{tripId}/groups`       | Add a tour group                                                           | Planned          |
| Tour Group | PUT    | `/api/groups/{groupId}`            | Update a tour group                                                        | Planned          |
| Tour Group | DELETE | `/api/groups/{groupId}`            | Remove/cancel a group                                                      | Planned          |
| Booking    | POST   | `/api/bookings`                    | Book a tour group                                                          | Planned          |
| Booking    | GET    | `/api/bookings/{id}`               | View a specific booking                                                    | Planned          |
| Booking    | GET    | `/api/users/{id}/bookings`         | List bookings by user                                                      | Planned          |
| Booking    | PUT    | `/api/bookings/{id}/cancel`        | Cancel a booking                                                           | Planned          |
| Image      | GET    | `/api/images/presigned-url`        | Get aws s3 pre-signed url                                                  | 👉🏻 In progress |
| Image      | POST   | `/api/images`                      | Upload image and return image url                                          | 👉🏻 In progress |
| Image      | GET    | `/api/images/{id}`                 | Retrieve image                                                             | Planned          |
| Image      | DELETE | `/api/images/{id}`                 | Delete image                                                               | Planned          |
| Auth       | POST   | `/api/auth/register`               | Register user/supplier                                                     | Planned          |
| Auth       | POST   | `/api/auth/login`                  | Login and receive token                                                    | Planned          |
| User       | GET    | `/api/users/me`                    | Get current user profile                                                   | Planned          |
| User       | PUT    | `/api/users/me`                    | Update profile                                                             | Planned          |
| Review     | POST   | `/api/trips/{id}/reviews`          | Submit a review                                                            | Planned          |
| Review     | GET    | `/api/trips/{id}/reviews`          | List all reviews for a trip                                                | Planned          |


## 👉🏻 Tech Stack

| Category                  | Technologies / Tools                                               |
|---------------------------|--------------------------------------------------------------------|
| **Language & Frameworks** | Java 17, Spring Boot 3, Spring Data JDBC, Spring AOP               |
| **Database**              | PostgreSQL (JDBC driver)                                           |
| **Cloud Services**        | AWS S3 (via AWS SDK v2), STS (optional presigned URL support)      |
| **Logging**               | Log4j2, SLF4J binding, YAML config support (Jackson YAML)          |
| **Testing**               | JUnit 5, Mockito (included via `spring-boot-starter-test`)         |
| **Build & Dev Tools**     | Maven, Spring Boot Devtools, IntelliJ IDEA, Git, GitHub            |
| **Code Utilities**        | Lombok (with annotation processing), `maven-compiler-plugin` setup |


## 👉🏻 Project Structure

```yml
src
├── main
│   └── java
│       └── com.vic.lovelytrip
│           ├── aspect
│           ├── common
│           ├── config
│           ├── controller
│           ├── dto
│           ├── entity
│           ├── mapper
│           ├── repository
│           ├── service
│           ├── validator
│           └── LovelyTripApplication
├── test
│   └── java
│       └── com.vic.lovelytrip
│           ├── aspect      
│           ├── common      
│           ├── mapper      
│           ├── repository      # Integration tests for API endpoints
│           ├── service         # Mock-based service layer unit tests
│           ├── validator       # Validator logic and error injection tests


```

💡 Tests are organized to mirror the main source tree and cover controller (integration), service (mock-based), and validator (unit) logic.


## 👉🏻 Scalability Plans

* Implement complete **Tour Group management**, including:
  * CRUD operations
  * Date, capacity, and price validation logic

* Enable **image upload and storage** for trip and tour group media

* Develop the **User Module**, including:
  * User registration and profile management
  * JWT-based authentication and authorization

* Improve **trip search functionality** by adding:
  * Pagination
  * Sorting options

* Set up a **CI/CD pipeline** for automated testing and deployment

* Containerize the application for **Docker-based deployment** and future cloud hosting


## 👉🏻 Contribution

This project is a personal backend demo to showcase software engineering best practices. Feel free to suggest improvements, or review the design!
