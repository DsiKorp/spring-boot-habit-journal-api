# Habit Journal API Documentation

This document describes the API documentation for the Habit Journal API.

## Swagger/OpenAPI Documentation

The API documentation is automatically generated using SpringDoc OpenAPI and is available in multiple formats:

### Interactive Swagger UI
When the application is running, access the interactive Swagger UI at:
- **URL**: http://localhost:8080/swagger-ui.html

### OpenAPI JSON Specification
The raw OpenAPI specification in JSON format is available at:
- **URL**: http://localhost:8080/v3/api-docs

### OpenAPI YAML Specification
A static OpenAPI 3.0 specification is available in this directory:
- **File**: `openapi.yaml`

## API Endpoints

The API provides the following endpoints for managing habits:

### Habits
- **POST /v1/habits** - Create a new habit
- **GET /v1/habits** - Retrieve all habits
- **GET /v1/habits/{id}** - Retrieve a specific habit by ID
- **PUT /v1/habits/{id}** - Update an existing habit
- **DELETE /v1/habits/{id}** - Delete a habit

## Models

### HabitRequestDTO
Used for creating and updating habits:
- `name` (string, required): The name of the habit

### HabitResponseDTO
Returned when retrieving habits:
- `id` (integer): Unique identifier of the habit
- `name` (string): The name of the habit

## Running the Application

To run the application and view the documentation:

```bash
./mvnw spring-boot:run
```

Then navigate to http://localhost:8080/swagger-ui.html to view the interactive documentation.

## Generated Documentation

Since the project includes the `springdoc-openapi-starter-webmvc-ui` dependency, the OpenAPI documentation is automatically generated based on the controller annotations and model classes. The static `openapi.yaml` file in this directory provides a reference specification of the intended API.