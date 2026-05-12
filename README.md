# School Attendance System

A Spring Boot application for managing students and attendance records.

## Overview

This project provides RESTful endpoints to create, read, update, and delete students and attendance records. It uses Spring Boot, Spring Data JPA, Lombok, and can run with MySQL as the primary datasource.

## Technologies

- Java 17
- Spring Boot 3.5.7
- Spring Data JPA
- Spring Web
- Spring Boot Actuator
- Lombok
- MySQL (configured in `src/main/resources/application.properties`)
- H2 database dependency is included and can be used with configuration adjustments

## Project Structure

- `src/main/java/com/school2` - application source code
  - `controller` - REST controllers for students and attendance
  - `dto` - data transfer objects
  - `mapper` - mapping between entities and DTOs
  - `model` - JPA entities
  - `repository` - Spring Data JPA repositories
  - `service` - business logic interfaces and implementations
- `src/main/resources/application.properties` - application configuration

## Running the application

1. Configure your database in `src/main/resources/application.properties`.
   - Current configuration is for a MySQL database at `jdbc:mysql://localhost:3306/schoolapi`.
2. Build and run the application using Gradle:

```bash
gradle bootRun
```

Or using the Gradle wrapper:

```bash
./gradlew bootRun
```

3. The application will start on `http://localhost:8080` by default.

## API Endpoints

### Students

- `GET /api/students` - list all students
- `GET /api/students/{id}` - get a student by ID
- `POST /api/students` - create a new student
- `PUT /api/students/{id}` - update an existing student
- `DELETE /api/students/{id}` - delete a student

### Attendance

- `GET /api/attendance` - list all attendance records
- `GET /api/attendance/{id}` - get an attendance record by ID
- `GET /api/attendance/students/{studentId}` - list attendance records for a specific student
- `POST /api/attendance` - create a new attendance record
- `POST /api/attendance/students/{studentId}` - create attendance for a specific student
- `PUT /api/attendance/{id}` - update an attendance record by ID
- `PUT /api/attendance/students/{studentId}` - update attendance by student ID
- `DELETE /api/attendance/{id}` - delete an attendance record by ID
- `DELETE /api/attendance/students/{studentId}` - delete attendance records by student ID

## Notes

- DTOs are used for attendance responses to include student details while avoiding cyclic serialization issues.
- `spring.jpa.hibernate.ddl-auto=update` is enabled in properties for schema updates during development. This setting is convenient for development but should be disabled or removed in production to avoid unexpected schema changes.


