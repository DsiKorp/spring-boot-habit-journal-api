package com.habitjournal.habit_journal_api.mongo_app.repository;

import com.habitjournal.habit_journal_api.mongo_app.model.HabitMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitMongoRepository extends MongoRepository<HabitMongo, String>, CustomHabitMongoRepository {
    Optional<HabitMongo> findByName(String name);
}
