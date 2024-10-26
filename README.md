# CourseConnect - Course Management System

The **CourseConnect** application is a Spring Boot-based web application designed to manage course creation, enrollment, and user accounts for both teachers and students. This application provides a simple API for teachers to create and manage courses and allows students to enroll in the courses of their choice. The system also includes functionalities for managing user accounts, ensuring a seamless experience for both educators and learners.

## Table of Contents
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Error Handling](#error-handling)
- [Contributing](#contributing)

## Tech Stack
- **Java 17**
- **Spring Boot 3.x**
- **Maven** (for dependency management)
- **PostgreSQL** (for production database)
- **H2 Database** (for in-memory testing and development)
- **Spring Data JPA** (for repository pattern and data access)
- **Hibernate** (ORM)
- **MockMVC** (for integration tests)
- **JUnit 5** (for unit tests)
- **Mockito** (for mocking dependencies in tests)
- **Postman** (for API testing)

## Features
- **User Management**: Teachers and students can create accounts.
- **Course Management**: Teachers can create, update, retrieve, and delete courses.
- **Enrollment Management**: Students can join courses created by teachers, with functionality to manage their enrollments.
- **Custom Exceptions**: Handle exceptions with meaningful messages, including custom exceptions for user and course management.
- **Validation**: Ensure valid inputs using `@Valid`, `@NotNull`, `@Min`, `@Max`, and other validation annotations.
- **Testing**: Comprehensive unit and integration testing using MockMVC, JUnit, and Mockito.

## Installation
### Prerequisites
- **Java 17**
- **PostgreSQL**
- **Maven**

### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/courseconnect.git
    ```

2. Configure PostgreSQL:
   - Set up a PostgreSQL database. You can name it `courseconnect`.
   - Update the credentials in `application.properties` to match your PostgreSQL setup:
     - Replace `your_db_user` and `your_db_password` with your actual PostgreSQL username and password.

3. Run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```

4. Access the APIs using Postman or your preferred HTTP client.

   Alternatively, run the JAR file:
    ```bash
    java -jar target/CourseConnect.jar
    ```

## API Endpoints
   
![Screenshot 2024-10-26 195402](https://github.com/user-attachments/assets/06177dc5-9b3f-4b82-ac98-f5600c15847a)

## Testing
- Unit tests for service and controller layers.
- Integration tests for API endpoints using MockMVC.

## Error Handling
- Custom exceptions to handle different error scenarios.
- Meaningful error messages returned for client requests.