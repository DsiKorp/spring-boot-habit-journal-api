package com.habitjournal.habit_journal_api.mongo_app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "habits_mongo")
public class HabitMongo {
    @Id
    private String id;
    private String name;

    List<LogEntryMongo> logEntries = new ArrayList<>();
}
