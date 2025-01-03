# Quiz Maker

**Quiz Maker** is a web application built with **Java**, **Spring Boot**, and **Thymeleaf**. It enables users to create, take, and view quizzes. The application supports user authentication, role-based access control, and provides a responsive and user-friendly interface.

---

## Table of Contents

- [Features](#features)
- [Modules](#modules)
- [Installation and Setup](#installation-and-setup)
- [Build and Run](#build-and-run)
- [Workflow](#workflow)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **Secure Authentication**: Role-based access control for users.
- **Quiz Creation**: Create quizzes by adding multiple questions under a chosen title.
- **Quiz Participation**: Take quizzes by answering questions and submitting answers.
- **Quiz History**: View previously created or taken quizzes.
- **Responsive Design**: A seamless user experience on various devices.

---

## Modules

### Controller
- **`MyController.java`**: Handles HTTP requests and routes them to appropriate services or views.

### Service
- **`UserService.java`**: Manages user-related functionalities such as authentication and role assignment.
- **`QuestionService.java`**: Handles operations related to quiz questions.

### Model
- **`User.java`**: Represents user entity fields like username, password, and roles.
- **`Question.java`**: Represents question entity fields like quiz title, question content, and options.

### Templates
- **HTML Pages**:
  - **`home.html`**: Application homepage.
  - **`signup.html`**: User registration form.
  - **`login.html`**: User login form.
  - **`titleSelection.html`**: Quiz title selection for creators.
  - **`addQuestion.html`**: Form to add questions to a quiz.
  - **`previousQuizz.html`**: Displays previously created or taken quizzes.
  - **`titleSelectionUser.html`**: Quiz title selection for participants.
  - **`quizzPage.html`**: Interface for taking quizzes.

### Static Resources
- **`style.css`**: Contains the styles and design of the application.

---

## Installation and Setup

### Prerequisites
- **Java Development Kit (JDK)**: Version 11 or higher.
- **Maven**: For managing dependencies and building the project.
- **Git**: For cloning the repository.

### Steps to Set Up

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/santosh4568/quiz-maker.git
   cd quiz-maker
2. **Install Dependencies: Ensure Maven is installed, then run:**
   ```bash
   mvn clean install
3. **Configuration**
   create a database and then configure application.properties.
3. **Run the Application:**
   ```bash
   mvn spring-boot:run
4. **Access the Application:**
   ```bash
   http://localhost:8080.
   
