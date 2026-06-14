package com.habitjournal.habit_journal_api.service;

import java.util.List;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.controller.dto.HabitResponseDTO;


public interface HabitService {
    HabitResponseDTO createNewHabit(HabitRequestDTO habitRequestDTO);
    List<HabitResponseDTO> findAllHabits();
}
