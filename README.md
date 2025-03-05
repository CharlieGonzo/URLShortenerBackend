# URL Shortener Backend

## Overview
This is the backend service for a URL shortener application. It provides API endpoints to shorten URLs, redirect users to the original URL, and manage stored URLs. The backend is built with Java and Spring Boot.

## Features
- Shorten long URLs into compact, shareable links.
- Redirect users to the original URL when accessing a shortened link.
- Store and retrieve URL mappings from a database.
- Basic analytics to track link usage (optional feature).

## Tech Stack
- **Backend:** Java, Spring Boot
- **Database:** PostgreSQL
- **Build Tool:** Maven/Gradle
- **Containerization (Optional):** Docker
- **Authentication (Future Scope):** JWT Authentication

## Installation & Setup
### Prerequisites
Ensure you have the following installed:
- Java 17+
- Maven or Gradle
- PostgreSQL (or update the application to use an in-memory database like H2 for testing)
- Docker (optional)

### Steps to Run Locally
1. Clone the repository:
   ```sh
   git clone https://github.com/CharlieGonzo/URLShortenerBackend.git
   cd URLShortenerBackend
   ```
2. Configure database settings in `application.properties` or `application.yml`.
3. Build the project:
   ```sh
   ./mvnw clean install   # If using Maven
   # OR
   ./gradlew build       # If using Gradle
   ```
4. Run the application:
   ```sh
   ./mvnw spring-boot:run   # For Maven
   # OR
   java -jar target/url-shortener.jar   # If built with Maven
   ```
5. The application should be running on `http://localhost:8080`.

## API Endpoints
### 1. Shorten a URL
- **Endpoint:** `POST /api/shorten`
- **Request Body:**
  ```json
  {
    "originalUrl": "https://example.com"
  }
  ```
- **Response:**
  ```json
  {
    "shortUrl": "http://localhost:8080/s/abc123"
  }
  ```

### 2. Redirect to Original URL
- **Endpoint:** `GET /s/{shortUrl}`
- **Response:** Redirects to the original URL.

### 3. Get All URLs (Admin Access - Optional)
- **Endpoint:** `GET /api/urls`
- **Response:**
  ```json
  [
    {
      "shortUrl": "abc123",
      "originalUrl": "https://example.com",
      "createdAt": "2024-03-05T10:00:00Z"
    }
  ]
  ```

## Future Enhancements
- Implement authentication for managing user-specific URLs.
- Add URL expiration and analytics tracking.
- Implement a frontend UI for ease of use.

## Contributing
Feel free to fork the repo, submit issues, or create pull requests. Contributions are welcome!

## License
This project is licensed under the MIT License.

---

For any issues or feature requests, please open an issue in the [GitHub repository](https://github.com/CharlieGonzo/URLShortenerBackend/issues).

