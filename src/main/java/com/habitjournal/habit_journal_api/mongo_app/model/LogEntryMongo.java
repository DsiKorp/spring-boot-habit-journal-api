package com.habitjournal.habit_journal_api.mongo_app.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogEntryMongo {
    private LocalDateTime entryDate;
}

