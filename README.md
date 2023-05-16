# Smart-Tribune-Test-Hassan

## Description

This is a RESTful API for a question and answer system. It allows users to create, retrieve, update and delete questions, as well as answer them. 

## Technologies used

- Kotlin
- Spring Boot
- PostgreSQL
- Hibernate
- Swagger

## Requirements

- Java 11
- PostgreSQL

## How to run

To run the project, you can use an IDE of your choice, such as IntelliJ or Eclipse. Follow the steps below:

1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Make sure you have Java 11 or higher installed.
4. Create a PostgreSQL database and update the application.properties file with your database credentials.
5. Run the application.
6. The application will be available at `http://localhost:8080`.

## API Documentation

The API documentation is not yet available.


Note: Before running the application, make sure that you have created the necessary tables in your PostgreSQL database. You can do this by running the schema.sql script located in the src/main/resources directory.

To run the application, you can either run the main function in the Application.kt file from your IDE or build the project using Gradle and run the jar file generated in the build/libs directory using the following command:

java -jar qanda-0.0.1-SNAPSHOT.jar

Make sure to replace qanda-0.0.1-SNAPSHOT.jar with the name of the jar file generated in your build.

