# ForoHub API

ForoHub is a RESTful API built with Spring Boot for managing users, courses, and topics in a forum-like application.

## Table of Contents

- [ForoHub API](#forohub-api)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Technologies](#technologies)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [Running the Application](#running-the-application)
  - [API Endpoints](#api-endpoints)
  - [Testing](#testing)
  - [Contributing](#contributing)
  - [License](#license)
  - [Author](#author)

## Features

- User management (create, read, update, delete)
- Course management (create, read, update, delete)
- Topic management (create, read, update, delete)
- Validation and error handling
- JWT-based authentication and authorization

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Web
- Hibernate
- MySQL (for production and testing)
- Flyway Migration
- Lombok
- Swagger (OpenAPI)
- Auth0
- JUnit 5
- Mockito

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- MySQL (for production)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/forohub.git
   cd forohub
   ```
2. Configure the database:

    Update the application.properties file in resources with your database configuration.
3. Build the project:
   ```bash
   mvn clean install
   ```

## Running the Application

1. Ensure your MySQL database is running and properly configured.
2. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
3. The application will start on `http://localhost:8080`.

## API Endpoints

User Endpoints
- GET /users: Get all users
- GET /users/{id}: Get user by ID
- POST /users: Create a new user
- PUT /users/{id}: Update user by ID
- DELETE /users/{id}: Delete user by ID

Course Endpoints
- GET /courses: Get all courses
- GET /courses/{id}: Get course by ID
- POST /courses: Create a new course
- PUT /courses/{id}: Update course by ID

Topic Endpoints
- GET /topics: Get all topics
- GET /topics/{id}: Get topic by ID
- POST /topics: Create a new topic
- PUT /topics/{id}: Update topic by ID
- DELETE /topics/{id}: Delete topic by ID

## Testing

1. To run the tests, use the following Maven command:
   ```bash
   mvn test
   ```
2. Ensure you have a test database configured in your application.properties file.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Author

**Andrés Echeverría**  
Fordragon Dev Company.