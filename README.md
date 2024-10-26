CourseConnect - Course Management System
The CourseConnect application is a Spring Boot-based web application designed to manage course creation, enrollment, and user accounts for both teachers and students. This application provides a simple API for teachers to create and manage courses and allows students to enroll in the courses of their choice. The system also includes functionalities for managing user accounts, ensuring a seamless experience for both educators and learners.

Table of Contents
Tech Stack
Features
Project Structure
Installation
Running the Application
API Endpoints
Testing
Error Handling
Contributing
Tech Stack
Java 17
Spring Boot 3.x
Maven (for dependency management)
PostgreSQL (for production database)
H2 Database (for in-memory testing and development)
Spring Data JPA (for repository pattern and data access)
Hibernate (ORM)
MockMVC (for integration tests)
JUnit 5 (for unit tests)
Mockito (for mocking dependencies in tests)
Postman (for API testing)
Features
User Management: Teachers and students can create accounts.
Course Management: Teachers can create, update, retrieve, and delete courses.
Enrollment Management: Students can join courses created by teachers, with functionality to manage their enrollments.
Custom Exceptions: Handle exceptions with meaningful messages, including custom exceptions for user and course management.
Validation: Ensure valid inputs using @Valid, @NotNull, @Min, @Max, and other validation annotations.
Testing: Comprehensive unit and integration testing using MockMVC, JUnit, and Mockito.
Project Structure
bash
Copy code
src/
├── main/
│   ├── java/com/courseconnect
│   │   ├── controllers/        # REST Controllers
│   │   ├── exceptions/         # Exception handling
│   │   ├── models/             # DTOs and Entity classes
│   │   ├── repositories/       # Data Repositories
│   │   └── services/           # Service implementations
│   └── resources/
│       ├── application.properties  # Configuration
│       └── data.sql                # Optional data script
└── test/                           # Unit and integration tests
Installation
Prerequisites
Java 17
PostgreSQL
Maven
Steps
Clone the repository:

bash
Copy code
git clone https://github.com/yourusername/courseconnect.git
Configure PostgreSQL:

Set up a PostgreSQL database. You can name it courseconnect.
Update the credentials in application.properties to match your PostgreSQL setup:
Replace your_db_user and your_db_password with your actual PostgreSQL username and password.
Run the application using Maven:

bash
Copy code
mvn spring-boot:run
Access the APIs using Postman or your preferred HTTP client.

Alternatively, run the JAR file:

bash
Copy code
java -jar target/CourseConnect.jar
API Documentation
The app will be accessible at:

arduino
Copy code
http://localhost:8080
API Endpoints

![Screenshot 2024-10-26 195402](https://github.com/user-attachments/assets/06177dc5-9b3f-4b82-ac98-f5600c15847a)
