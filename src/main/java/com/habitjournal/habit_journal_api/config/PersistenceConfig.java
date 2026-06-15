package com.habitjournal.habit_journal_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.habitjournal.habit_journal_api.repository")
@EnableMongoRepositories(basePackages = "com.habitjournal.habit_journal_api.mongo_app.repository")
public class PersistenceConfig {
}