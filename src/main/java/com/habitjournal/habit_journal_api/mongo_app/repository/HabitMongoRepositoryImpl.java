package com.habitjournal.habit_journal_api.mongo_app.repository;

import com.habitjournal.habit_journal_api.mongo_app.model.HabitMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class HabitMongoRepositoryImpl implements CustomHabitMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<HabitMongo> findHabitsLoggedSince(LocalDateTime sinceDate) {

        Query query = new Query();

        query.addCriteria(
                Criteria.where("logEntries").elemMatch(
                        Criteria.where("entryDate").gte(sinceDate) // mayor o igual a la fecha
                ));

        return mongoTemplate.find(query, HabitMongo.class);
    }
}














