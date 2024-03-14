# JWT Authentication Project 0.12.5

This is a Java project that uses Spring Boot and Gradle. It implements JWT (JSON Web Token) authentication.

## Features

- User registration and login
- JWT generation for authenticated users
- JWT verification for protected endpoints

- 
## Technologies

- Java
- Spring Boot
- Gradle
- JWT
- MySQL

- ## Setup

  Use :
  SecretKey key = generateKey(256);
  System.out.println("Key: " + java.util.Base64.getEncoder().encodeToString(key.getEncoded()));

   Generate random secretKey for Jwt

The environment variables used in  application.yml file are:  
MYSQL_USER
MYSQL_PASSWORD
JWT_SECRET
These variables are used to configure the database connection and JWT secret . You should set these environment variables with the appropriate values in your deployment environment.
