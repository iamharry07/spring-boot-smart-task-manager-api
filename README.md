# Smart Task Manager

A full-stack Task Management application built with **Spring Boot, Spring Security, JWT, PostgreSQL, and React**.

The application allows authenticated users to create, manage, categorize, and track their tasks through a secure REST API and a user-friendly React interface.

---

## 📸 Application Screenshots

### Login

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/1616c7a4-a93a-480a-aa88-b900fa4051a0" />

### Register

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/3ac01811-8210-4101-be22-ca33f74e7085" />

### Dashboard

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/1ea2ec74-ca20-4169-896e-d10d4ebbf82b" />


### Task Management

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/f2c4af2a-18fb-4dd6-952e-9fb090d434a4" />

### Create Task

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/68b3fb23-f296-4064-9a18-e41144aadcb5" />

---

## 🚀 Features

### Authentication & Authorization

- User registration
- User login
- JWT-based authentication
- Access token validation
- Refresh token support
- Token expiration handling
- Secure logout
- Revoked token management
- Role-based authorization
- Password encryption

### Task Management

- Create tasks
- View tasks
- Update tasks
- Delete tasks
- Task status management
- Task priority management
- Due date support
- Task descriptions
- User-specific task management

### Category Management

- Create categories
- Manage task categories
- Associate tasks with categories

### Security

- Spring Security
- JWT authentication
- Custom JWT authentication filter
- Refresh token mechanism
- Revoked token validation
- Protected API endpoints
- Authentication exception handling

### Frontend

- React-based user interface
- Login and registration screens
- Task dashboard
- Task creation and management
- Category management
- API integration with Spring Boot backend
- Authentication-aware UI

---

## 🛠️ Tech Stack

### Backend

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- Maven

### Database

- PostgreSQL

### Frontend

- React
- JavaScript
- HTML
- CSS
- REST API

### Development Tools

- IntelliJ IDEA
- PostgreSQL / pgAdmin
- Git
- GitHub
- Postman

---

## 🏗️ Project Architecture

The backend follows a layered architecture:

Controller
    ↓
Service
    ↓
Repository
    ↓
Database

## 📂 Project Structure

```text
SmartTaskManagerAPI/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.saqlain.SmartTaskManagerAPI/
│   │   │       │
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
│   │       └── application.properties
│   │
│   ├── test/
│   │
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── ...
│
├── screenshots/
│   ├── login.png
│   ├── register.png
│   ├── dashboard.png
│   ├── tasks.png
│   └── create-task.png
│
├── .gitignore
├── README.md
├── mvnw
├── mvnw.cmd
└── pom.xml
