# ✨ LovelyTrip REST API

LovelyTrip is a Spring Boot backend project demonstrating clean architecture and best practices in building maintainable, testable RESTful services. It includes core features like trip search, trip creation, and fetching trip details.


## 👉🏻 Features

-  **Search Trips** with flexible criteria
-  **Get Trip by ID** with validation and centralized error handling
-  **Create a New Trip** with image reference mapping



## 👉🏻 System Design Highlights


| Principle                      | Description                                                                                       | Reference file                                                                                                                                                                                                                                                           |
|--------------------------------|---------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clean Architecture**         | Structured layers (controller → service → repository) for clear separation and maintainability    | [TripServiceImpl.java](https://github.com/yourname/your-repo/blob/main/src/main/java/com/vic/lovelytrip/service/TripServiceImpl.java)                                                                                                                                    |
| **DTO Pattern**                | Decouples API and internal models using DTOs for clean contracts and better encapsulation         | [TripCreateRequest.java](https://github.com/yourname/your-repo/blob/main/src/main/java/com/vic/lovelytrip/dto/TripCreateRequest.java), [TripResponse.java](https://github.com/yourname/your-repo/blob/main/src/main/java/com/vic/lovelytrip/dto/TripResponse.java)       |
| **Validation Layer**           | Centralized validators enforce business rules and promote reuse                                   | [TripValidator.java](https://github.com/yourname/your-repo/blob/main/src/main/java/com/vic/lovelytrip/validator/TripValidator.java), [BaseValidator.java](https://github.com/yourname/your-repo/blob/main/src/main/java/com/vic/lovelytrip/validator/BaseValidator.java) |
| **Traceable Logging**          | Uses `@Traceable` to auto-log method calls via AOP, improving observability with zero code impact | [TraceableAspect.java](https://github.com/yourname/your-repo/blob/main/src/main/java/com/vic/lovelytrip/aspect/TraceableAspect.java), [Traceable.java](https://github.com/yourname/your-repo/blob/main/src/main/java/com/vic/lovelytrip/lib/Traceable.java)              |
| **Global Exception Handling**  | Handles errors via `BusinessException` and structured error responses                             | [HttpExceptionHandlingAspect.java](https://github.com/yourname/your-repo/blob/main/src/main/java/com/vic/lovelytrip/aspect/HttpExceptionHandlingAspect.java)                                                                                                             |
| **Mapper Conversion**          | Dedicated mappers convert between DTOs and entities for consistency and testability               | [TripMapper.java](https://github.com/yourname/your-repo/blob/main/src/main/java/com/vic/lovelytrip/mapper/TripMapper.java)                                                                                                                                               |



## 👉🏻 API Endpoints Overview

| Module     | Method   | Endpoint                         | Description                                                                | Status             |
|------------|----------|----------------------------------|----------------------------------------------------------------------------|--------------------|
| Trip       | GET      | `/trips/?query=`                 | Handles normal search, advanced filters, or returns all trips if no params | Planned            |
| Trip       | GET      | `/trips/{id}?includeTourGroups=` | Retrieve one trip by ID                                                    | Completed          |
| Trip       | POST     | `/trips`                         | Create a new trip                                                          | Completed          |
| Trip       | PUT      | `/trips/{id}`                    | Update an existing trip                                                    | Planned            |
| Trip       | DELETE   | `/trips/{id}`                    | Soft-delete a trip                                                         | Planned            |
| Tour Group | GET      | `/api/trips/{tripId}/groups`     | List groups under a trip                                                   | Planned            |
| Tour Group | GET      | `/api/groups/{groupId}`          | Get group schedule and pricing                                             | Planned            |
| Tour Group | POST     | `/api/trips/{tripId}/groups`     | Add a tour group                                                           | Planned            |
| Tour Group | PUT      | `/api/groups/{groupId}`          | Update a tour group                                                        | Planned            |
| Tour Group | DELETE   | `/api/groups/{groupId}`          | Remove/cancel a group                                                      | Planned            |
| Booking    | POST     | `/api/bookings`                  | Book a tour group                                                          | Planned            |
| Booking    | GET      | `/api/bookings/{id}`             | View a specific booking                                                    | Planned            |
| Booking    | GET      | `/api/users/{id}/bookings`       | List bookings by user                                                      | Planned            |
| Booking    | PUT      | `/api/bookings/{id}/cancel`      | Cancel a booking                                                           | Planned            |
| Image      | POST     | `/api/images`                    | Upload image and return image url                                          | 👉🏻 In progress   |
| Image      | GET      | `/api/images/{id}`               | Retrieve image                                                             | Planned            |
| Image      | DELETE   | `/api/images/{id}`               | Delete image                                                               | Planned            |
| Auth       | POST     | `/api/auth/register`             | Register user/supplier                                                     | Planned            |
| Auth       | POST     | `/api/auth/login`                | Login and receive token                                                    | Planned            |
| User       | GET      | `/api/users/me`                  | Get current user profile                                                   | Planned            |
| User       | PUT      | `/api/users/me`                  | Update profile                                                             | Planned            |
| Review     | POST     | `/api/trips/{id}/reviews`        | Submit a review                                                            | Planned            |
| Review     | GET      | `/api/trips/{id}/reviews`        | List all reviews for a trip                                                | Planned            |



| Module     | Method | Endpoint                         | Description                                                                | Status           | Returns                            |
|------------|--------|----------------------------------|----------------------------------------------------------------------------|------------------|------------------------------------|
| Trip       | GET    | `/trips?query=`                  | Handles normal search, advanced filters, or returns all trips if no params | Planned          | `List<TripResponse>`               |
| Trip       | GET    | `/trips/{id}?includeTourGroups=` | Retrieve one trip by ID                                                    | Completed        | `TripResponse`                     |
| Trip       | POST   | `/trips`                         | Create a new trip                                                          | Completed        | `TripResponse` (created)           |
| Trip       | PUT    | `/trips/{id}`                    | Update an existing trip                                                    | Planned          | `TripResponse` (updated)           |
| Trip       | DELETE | `/trips/{id}`                    | Soft-delete a trip                                                         | Planned          | `204 No Content`                   |
| Tour Group | GET    | `/api/trips/{tripId}/groups`     | List groups under a trip                                                   | Planned          | `List<TourGroupResponse>`          |
| Tour Group | GET    | `/api/groups/{groupId}`          | Get group schedule and pricing                                             | Planned          | `TourGroupResponse`                |
| Tour Group | POST   | `/api/trips/{tripId}/groups`     | Add a tour group                                                           | Planned          | `TourGroupResponse` (created)      |
| Tour Group | PUT    | `/api/groups/{groupId}`          | Update a tour group                                                        | Planned          | `TourGroupResponse` (updated)      |
| Tour Group | DELETE | `/api/groups/{groupId}`          | Remove/cancel a group                                                      | Planned          | `204 No Content`                   |
| Booking    | POST   | `/api/bookings`                  | Book a tour group                                                          | Planned          | `BookingResponse` (created)        |
| Booking    | GET    | `/api/bookings/{id}`             | View a specific booking                                                    | Planned          | `BookingResponse`                  |
| Booking    | GET    | `/api/users/{id}/bookings`       | List bookings by user                                                      | Planned          | `List<BookingResponse>`            |
| Booking    | PUT    | `/api/bookings/{id}/cancel`      | Cancel a booking                                                           | Planned          | `BookingResponse` (updated status) |
| Image      | POST   | `/api/images`                    | Upload image and return image url                                          | Planned          | `URL`                              |
| Image      | GET    | `/api/images`                    | Get preSignedS3url                                                         | 👉🏻 In progress | `ImageUploadResponse (imageUrl)`   |
| Image      | GET    | `/api/images/{id}`               | Retrieve image                                                             | Planned          | `ImageResponse (binary or URL)`    |
| Image      | DELETE | `/api/images/{id}`               | Delete image                                                               | Planned          | `204 No Content`                   |
| Auth       | POST   | `/api/auth/register`             | Register user/supplier                                                     | Planned          | `AuthResponse (user, token)`       |
| Auth       | POST   | `/api/auth/login`                | Login and receive token                                                    | Planned          | `AuthResponse (user, token)`       |
| User       | GET    | `/api/users/me`                  | Get current user profile                                                   | Planned          | `UserProfileResponse`              |
| User       | PUT    | `/api/users/me`                  | Update profile                                                             | Planned          | `UserProfileResponse (updated)`    |
| Review     | POST   | `/api/trips/{id}/reviews`        | Submit a review                                                            | Planned          | `ReviewResponse (created)`         |
| Review     | GET    | `/api/trips/{id}/reviews`        | List all reviews for a trip                                                | Planned          | `List<ReviewResponse>`             |



## 👉🏻 Testing Strategy

* **Unit Tests** for validators, service logic, and DTO mappers
* **Integration Tests** using `@SpringBootTest` and `@DataJdbcTest`
* **Mocking** with Mockito for repository/service isolation



## 👉🏻 Tech Stack

* Java 17
* Spring Boot 3
* Spring Data JDBC
* PostgreSQL
* Log4j2 + SLF4J
* JUnit 5 + Mockito



## 👉🏻 Project Structure

```yml
src
├── main
│   └── java
│       └── com.vic.lovelytrip
│           ├── aspect
│           ├── controller
│           ├── dto
│           ├── entity
│           ├── lib
│           ├── mapper
│           ├── repository
│           ├── service
│           ├── validator
│           ├── ApplicationConfig
│           └── LovelyTripApplication
├── test
│   └── java
│       └── com.vic.lovelytrip
│           ├── controller      # Integration tests for API endpoints
│           ├── service         # Mock-based service layer unit tests
│           ├── validator       # Validator logic and error injection tests
│           └── mapper          # DTO ↔ Entity mapping tests


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
