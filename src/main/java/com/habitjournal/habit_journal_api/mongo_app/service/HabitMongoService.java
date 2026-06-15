package com.habitjournal.habit_journal_api.mongo_app.service;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.mongo_app.controller.dto.HabitMResponseDTO;


import java.util.List;

public interface HabitMongoService {
    HabitMResponseDTO createNewHabit(HabitRequestDTO requestDTO);

    List<HabitMResponseDTO> findAllHabits();

    List<HabitMResponseDTO> findHabitsLoggedSince(int days);
}
