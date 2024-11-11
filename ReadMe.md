# Car Showroom Management Application

This project is a full-stack application for managing car showrooms and their associated cars. It includes a backend developed with Java and Spring Boot and a frontend developed with Angular. This README provides a guide on setting up and running both parts of the application.

## Project Structure

- **Backend**: Java Spring Boot application, using Spring Data JPA for database management.
- **Frontend**: Angular application for managing showrooms and cars, interacting with the backend APIs.

## Prerequisites

- **Backend**:
  - Java (latest stable version)
  - Maven
  - Database (e.g., MySQL, PostgreSQL)

- **Frontend**:
  - Node.js (latest stable version)
  - Angular CLI

## Setup Instructions

### 1. Backend

1. Clone the repository and navigate to the backend directory.
2. Install dependencies using Maven:
   ```bash
   mvn clean install

3. Set up a database (e.g., MySQL or you can use H2) and configure the connection settings in application.properties or application.yml.
4. Start the backend server:
   ```bash
   mvn spring-boot:run

The backend server should be accessible on `http://localhost:8080`.


## API Endpoints

### Car Showroom APIs

- **Create Car Showroom**: `POST /showrooms`
- **List Car Showrooms**: `GET /showrooms`
- **Get Car Showroom by ID**: `GET /showrooms/{id}`
- **Update Car Showroom**: `PUT /showrooms/{id}`
- **Delete Car Showroom**: `DELETE /showrooms/{id}` (soft delete)

### Car APIs

- **Add Car to Showroom**: `POST /showrooms/{id}/cars`
- **List Cars with Showroom Details**: `GET /carsWithShowRoom`

Refer to the Postman or Swagger documentation included for more details on request parameters and responses.

### 2. Frontend

1. Navigate to the frontend directory.
2. Install dependencies:
   ```bash
   npm install
3. Start the Angular application:
   ```bash
   ng serve

The frontend application should be accessible on `http://localhost:4200`.

## Key Features

### Backend
- CRUD operations for showrooms and cars.
- Pagination, sorting, and filtering support.
- Soft delete functionality for showrooms.
- Basic input validation.

### Frontend
- Soon..

## Assumptions and Limitations
- No authentication is required by default but can be added for enhanced security.

## Additional Notes
- Ensure the backend server is running on `localhost:8080` and frontend on `localhost:4200` unless configurations are changed.
- Refer to `application.properties` for any configurable settings for the database and server ports.
