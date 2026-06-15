# Solution for Bean Name Conflict between JPA and MongoDB Repositories

## Problem
The application fails to start with the error:
```
The bean 'habitMongoRepository', defined in com.habitjournal.habit_journal_api.mongo_app.repository.HabitMongoRepository defined in @EnableMongoRepositories declared on DataMongoRepositoriesRegistrar.EnableMongoRepositoriesConfiguration, could not be registered. A bean with that name has already been defined in com.habitjournal.habit_journal_api.mongo_app.repository.HabitMongoRepository defined in @EnableJpaRepositories declared on DataJpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration and overriding is disabled.
```

This occurs because both Spring Data JPA and Spring Data MongoDB are enabled and scanning the same base package (or overlapping packages), causing them both to try to create a bean for the `HabitMongoRepository` interface.

## Solution
Create a configuration class to explicitly specify the base packages for each repository type, preventing overlap.

### Step 1: Create a Configuration Class
Create `src/main/java/com/habitjournal/habit_journal_api/config/PersistenceConfig.java`:

```java
package com.habitjournal.habit_journal_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.habitjournal.habit_journal_api.repository")
@EnableMongoRepositories(basePackages = "com.habitjournal.habit_journal_api.mongo_app.repository")
public class PersistenceConfig {
}
```

### Step 2: Verify Repository Packages
- JPA repositories should be in: `com.habitjournal.habit_journal_api.repository`
- MongoDB repositories should be in: `com.habitjournal.habit_journal_api.mongo_app.repository`

### Step 3: Check Entity Annotations
**Important:** The same entity class cannot be used for both JPA and MongoDB without modification because:
- JPA requires `@Entity`
- MongoDB requires `@Document`

You have two options:
1. **Separate Entity Classes**: Create distinct classes for JPA and MongoDB models.
2. **Dual Annotation**: Annotate the class with both `@Entity` and `@Document` (not recommended due to potential conflicts).

Given your current `Habit` class is annotated with `@Entity`, you will need to:
- Either create a separate MongoDB model class (e.g., `HabitMongo`) with `@Document`
- Or modify the existing class to work with both (advanced, requires careful annotation)

### Step 4: Restart the Application
After making these changes, restart your Spring Boot application.

## Verification
The application should start without the bean name conflict error. If you encounter further issues related to entity persistence, review the annotations on your model classes.

## Additional Notes
- The `@Repository` annotation on your repository interfaces is optional but harmless.
- Ensure your MongoDB configuration (connection details) is present in `application.properties` or `application-dev.properties`.