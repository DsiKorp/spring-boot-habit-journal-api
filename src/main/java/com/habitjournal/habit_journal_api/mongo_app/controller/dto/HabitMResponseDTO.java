package com.habitjournal.habit_journal_api.mongo_app.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class HabitMResponseDTO {
    private String id;
    private String name;
    private List<LocalDateTime> logs;
}
