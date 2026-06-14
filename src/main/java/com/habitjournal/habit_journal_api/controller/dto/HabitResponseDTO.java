package com.habitjournal.habit_journal_api.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public record HabitResponseDTO(
        Long id,
        String name,
        List<LocalDateTime> logs
) {
}
