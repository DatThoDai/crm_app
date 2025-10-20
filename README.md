# CRM Project - Task Management System

A web-based CRM system for managing company projects and tasks, built with Java Servlet and MySQL.

## Features

### Role-based Access Control
- **Admin:** Full system management, user administration
- **Leader:** Project management, task assignment
- **Member:** Task updates and progress tracking

### Key Functions
- User and role management
- Project creation and tracking
- Task assignment and monitoring
- Real-time dashboard statistics
- Email notifications for task assignments

## Tech Stack

- **Backend:** Java Servlet, JSP
- **Frontend:** Bootstrap 5, JavaScript
- **Database:** MySQL
- **Email Service:** JavaMail API
- **Server:** Jetty (Embedded)
- **Deployment:** Railway Platform

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── config/         # Database configuration
│   │   ├── controller/     # Servlet controllers
│   │   ├── repository/     # Database operations
│   │   ├── service/        # Business logic
│   │   ├── entity/         # Data models
│   │   └── filter/         # Authentication & authorization
│   └── webapp/            
│       ├── WEB-INF/        # Web configuration
│       ├── css/            # Stylesheets
│       ├── js/             # JavaScript files
│       └── *.jsp           # JSP views
```

## Setup & Installation

1. Clone the repository
2. Configure MySQL database
3. Set environment variables:
   - `DATABASE_URL`
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`
   - `MAIL_USERNAME`
   - `MAIL_PASSWORD`
4. Build the project: `mvn clean package`
5. Run the application

## Database Schema

- **Users:** User information and authentication
- **Roles:** User role definitions (Admin, Leader, Member)
- **Jobs:** Project information and timelines
- **Tasks:** Individual tasks and assignments
- **Status:** Task status tracking

## Environment Variables

```env
DATABASE_URL=mysql://[username]:[password]@[host]:[port]/[database]
DATABASE_USERNAME=[username]
DATABASE_PASSWORD=[password]
MAIL_USERNAME=[email]
MAIL_PASSWORD=[app-password]
```

