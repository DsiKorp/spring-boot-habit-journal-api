package com.habitjournal.habit_journal_api.controller.dto;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

// record define un objeto inmutable,
// autogenera los constructores, getters, equals, hashCode y toString
public record HabitRequestDTO(
        @NotBlank(message = "El nombre del hábito no puede estar vacío.")
        String name,
        List<LocalDateTime> logs
) {
}
