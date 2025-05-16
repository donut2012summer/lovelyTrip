# ✨ LovelyTrip REST API

LovelyTrip is a Spring Boot backend project demonstrating clean architecture and best practices in building maintainable, testable RESTful services. It includes core features like trip search, trip creation, and fetching trip details.


## 👉🏻 Features

-  **Search Trips** with flexible criteria
-  **Get Trip by ID** with validation and centralized error handling
-  **Create a New Trip** with image reference mapping



## 👉🏻 System Design Highlights

| Principle                     | Implementation                                                             |
|-------------------------------|----------------------------------------------------------------------------|
| **Clean Architecture**        | Structured layers: controller → service → repository                       |
| **DTO Pattern**               | Input/output objects decoupled from entities via dedicated DTOs            |
| **Validation Layer**          | Centralized validators to enforce business rules                           |
| **Aspect-Oriented Logging**   | Logs handled via `@Aspect` to separate concerns                            |
| **Global Exception Handling** | Robust error handling using `BusinessException` and custom error responses |



## 👉🏻 API Endpoints Overview

| Module     | Method   | Endpoint                     | Description                        | Status          |
|------------|----------|------------------------------|------------------------------------|-----------------|
| Trip       | GET      | `/trips/search?query=`       | Keyword-based trip search          | ✅ Completed     |
| Trip       | GET      | `/trips/advanced-search`     | Advanced filters, pagination, etc. | 🛠️ In Progress |
| Trip       | GET      | `/trips/{id}`                | Retrieve one trip by ID            | ✅ Completed     |
| Trip       | POST     | `/trips`                     | Create a new trip                  | ✅ Completed     |
| Trip       | PUT      | `/trips/{id}`                | Update an existing trip            | 🕐 Planned      |
| Trip       | DELETE   | `/trips/{id}`                | Soft-delete a trip                 | 🕐 Planned      |
| Tour Group | GET      | `/api/trips/{tripId}/groups` | List groups under a trip           | 🕐 Planned      |
| Tour Group | GET      | `/api/groups/{groupId}`      | Get group schedule and pricing     | 🕐 Planned      |
| Tour Group | POST     | `/api/trips/{tripId}/groups` | Add a tour group                   | 🕐 Planned      |
| Tour Group | PUT      | `/api/groups/{groupId}`      | Update a tour group                | 🕐 Planned      |
| Tour Group | DELETE   | `/api/groups/{groupId}`      | Remove/cancel a group              | 🕐 Planned      |
| Booking    | POST     | `/api/bookings`              | Book a tour group                  | 🕐 Planned      |
| Booking    | GET      | `/api/bookings/{id}`         | View a specific booking            | 🕐 Planned      |
| Booking    | GET      | `/api/users/{id}/bookings`   | List bookings by user              | 🕐 Planned      |
| Booking    | PUT      | `/api/bookings/{id}/cancel`  | Cancel a booking                   | 🕐 Planned      |
| Image      | POST     | `/api/images`                | Upload image                       | 🕐 Planned      |
| Image      | GET      | `/api/images/{id}`           | Retrieve image                     | 🕐 Planned      |
| Image      | DELETE   | `/api/images/{id}`           | Delete image                       | 🕐 Planned      |
| Auth       | POST     | `/api/auth/register`         | Register user/supplier             | 🕐 Planned      |
| Auth       | POST     | `/api/auth/login`            | Login and receive token            | 🕐 Planned      |
| User       | GET      | `/api/users/me`              | Get current user profile           | 🕐 Planned      |
| User       | PUT      | `/api/users/me`              | Update profile                     | 🕐 Planned      |
| Review     | POST     | `/api/trips/{id}/reviews`    | Submit a review                    | 🕐 Planned      |
| Review     | GET      | `/api/trips/{id}/reviews`    | List all reviews for a trip        | 🕐 Planned      |




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
