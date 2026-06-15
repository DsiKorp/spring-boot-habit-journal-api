package com.habitjournal.habit_journal_api.mongo_app.repository;

import com.habitjournal.habit_journal_api.mongo_app.model.HabitMongo;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomHabitMongoRepository {
    List<HabitMongo> findHabitsLoggedSince(LocalDateTime sinceDate);
}
