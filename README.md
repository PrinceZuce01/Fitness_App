# Fitness Centre Management System (MVC)

## Overview

Fitness Centre Management System is a web-based application that helps manage fitness center members and users (employees). The system includes functionalities for user authentication, member management, and administrative operations.

## Features

### User Functions (Before/After Authorization)

- **Signup**
  - Admin
  - User (Employee)
- **Login**
  - Admin
  - User
- **Update User**
  - Admin
- **Delete User**
  - Admin
- **Forgot Password**
  - Admin
  - User
- **Change Password**
  - Admin
  - User
- **Get User Data**
  - Admin

### User Attributes

- ID
- Name
- Contact Number
- Email
- Password
- Status
- Role

### Member Functions

- Add New Member
- Update Member
- Delete Member
- Get All Members
- Get Member by ID

### Member Attributes

- ID
- Name
- Age
- Weight
- Height
- BMI

### Admin Functions

- Approve User Roles (Allow Employees to Access the System)
- Manage Members (Add, Update, Delete)
- Manage Users (Add, Update, Delete)

### User (Employee) Functions

- Require Role Approval by Admin
- View Member Details

---

## Setup Guide

### Technologies Used

- Java 21
- Spring Boot 3.4.3
- Angular 19
- Node.js 18
- MySQL

### Database Setup

1. Open MySQL Command Line.
2. Create your own fitness database:
   ```sql
   CREATE DATABASE fitapp;
   ```
3. Update `application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME
   spring.datasource.username=YOUR_DB_USERNAME
   spring.datasource.password=YOUR_DB_PASSWORD
   spring.mail.username=YOUR_EMAIL@gmail.com
   spring.mail.password=YOUR_EMAIL_PASSWORD
   ```
   _(Note: Use an app password if using Gmail. [Guide to Generate App Password](https://support.google.com/accounts/answer/185833?hl=en))_
4. Open `EmailUtils.java` and update the email settings to match `application.properties`.

---

## Running the Application

### Step 1: Start MySQL Service

Ensure your MySQL service is running before launching the application.

### Step 2: Start the Backend (Spring Boot)

```sh
mvn spring-boot:run
```

### Step 3: Access Swagger UI

Once the application starts, open your browser and go to:

```
http://localhost:8080/swagger-ui/index.html
```

This will show all available APIs for testing.

---

## API Usage

### 1. Sign Up (Create Admin/User Account)

**Endpoint:** `/user/signup`

- **Admin Registration:**

  ```json
  {
    "name": "Admin Name",
    "contactNumber": "123456789",
    "email": "admin@example.com",
    "password": "password123",
    "status": "true",
    "role": "admin"
  }
  ```

- **User (Employee) Registration:**
  ```json
  {
    "name": "User Name",
    "contactNumber": "123456789",
    "email": "user@example.com",
    "password": "password123"
  }
  ```

### 2. Login

**Endpoint:** `/user/login`

```json
{
  "email": "your_email",
  "password": "your_password"
}
```

Response:

```json
{
  "token": "your_auth_token"
}
```

- Copy the token and paste it into the **Authorize** button in Swagger UI to use other APIs.

### 3. Forgot Password

**Endpoint:** `/user/forgotPassword`

```json
{
  "email": "your_email"
}
```

- A new password will be sent to the registered email.

### 4. Change Password (After Login)

**Endpoint:** `/user/changePassword`

```json
{
  "oldPass": "old_password",
  "newPass": "new_password"
}
```

### 5. Add New Member (Admin Only)

**Endpoint:** `/member/addNewMember`

```json
{
  "name": "John Doe",
  "age": 30,
  "weight": 70,
  "height": 175
}
```

### 6. Update Member

**Endpoint:** `/member/updateMember`

```json
{
  "id": 2,
  "name": "Updated Name",
  "age": 32,
  "weight": 75,
  "height": 180
}
```

### 7. Delete Member

**Endpoint:** `/member/deleteMember/{id}`

- Replace `{id}` with the member's ID to delete.

---

## Notes

- Ensure MySQL is running before starting the application.
