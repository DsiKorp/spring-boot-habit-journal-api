package com.habitjournal.habit_journal_api.service;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
//import org.springframework.context.annotation.Primary;
import com.habitjournal.habit_journal_api.controller.dto.HabitResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
public class FakeHabitServiceImpl implements HabitService {

    @Override
    public HabitResponseDTO createNewHabit(HabitRequestDTO habitRequestDTO) {
        System.out.println("Fake Create Habit Service");
        return null;
    }

    @Override
    public List<HabitResponseDTO> findAllHabits() {
        return List.of();
    }
}
