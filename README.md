# Smart Task Manager API

A secure RESTful Task Management API built using Spring Boot, Spring Security, JWT Authentication, JPA/Hibernate, and MySQL.

The project provides user authentication, JWT-based authorization, task management, category management, refresh token handling, and secure logout with JWT revocation.

---

## 🚀 Features

### Authentication & Authorization

- User registration
- User login
- Password encryption using BCrypt
- JWT-based authentication
- Role-based authorization
- Access token validation
- Refresh token generation
- Refresh token persistence
- Secure logout
- JWT token revocation after logout

### Task Management

- Create tasks
- Get user-specific tasks
- Update tasks
- Delete tasks
- Task status management
- Task priority management
- Task due dates
- Task descriptions
- Task categories

### Category Management

- Create categories
- Retrieve categories
- Update categories
- Delete categories
- User-specific categories

### Security

- JWT authentication filter
- Stateless authentication
- Bearer token validation
- Revoked JWT detection
- Refresh token deletion during logout
- Unauthorized request protection
- Role-based access control

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- MySQL
- Lombok
- Maven

---

## 📂 Project Structure

```textSmartTaskManagerAPI/
│
├── .idea/
├── .mvn/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.saqlain.SmartTaskManagerAPI/
│   │   │
│   │   │       ├── config/
│   │   │       │   └── SecurityConfig.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── CategoryController.java
│   │   │       │   ├── HelloController.java
│   │   │       │   └── TaskController.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       │   ├── request/
│   │   │       │   │   ├── CategoryRequest.java
│   │   │       │   │   ├── LoginRequest.java
│   │   │       │   │   ├── RefreshTokenRequest.java
│   │   │       │   │   ├── RegisterRequest.java
│   │   │       │   │   ├── TaskRequest.java
│   │   │       │   │   └── UpdateTaskRequest.java
│   │   │       │   │
│   │   │       │   └── response/
│   │   │       │       ├── LoginResponse.java
│   │   │       │       ├── RefreshTokenResponse.java
│   │   │       │       └── TaskResponse.java
│   │   │       │
│   │   │       ├── entity/
│   │   │       │   ├── Category.java
│   │   │       │   ├── RefreshToken.java
│   │   │       │   ├── RevokedToken.java
│   │   │       │   ├── Roles.java
│   │   │       │   ├── Tasks.java
│   │   │       │   └── Users.java
│   │   │       │
│   │   │       ├── exception/
│   │   │       │   ├── EmailAlreadyExistsException.java
│   │   │       │   ├── ExpiredTokenException.java
│   │   │       │   ├── InvalidCredentialsException.java
│   │   │       │   ├── TaskNotFoundException.java
│   │   │       │   ├── TokenNotFoundException.java
│   │   │       │   └── UserNotFoundException.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── CategoryRepository.java
│   │   │       │   ├── RefreshTokenRepository.java
│   │   │       │   ├── RevokedTokenRepository.java
│   │   │       │   ├── RoleRepository.java
│   │   │       │   ├── TaskRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       │
│   │   │       ├── security/
│   │   │       │   └── JwtAuthenticationFilter.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── AuthService.java
│   │   │       │   ├── CategoryService.java
│   │   │       │   ├── CustomUserDetailsService.java
│   │   │       │   ├── JwtService.java
│   │   │       │   ├── RefreshTokenService.java
│   │   │       │   ├── RevokedTokenService.java
│   │   │       │   └── TaskService.java
│   │   │       │
│   │   │       └── SmartTaskManagerApiApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
│
├── target/
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
│
├── pom.xml
└── README.md
