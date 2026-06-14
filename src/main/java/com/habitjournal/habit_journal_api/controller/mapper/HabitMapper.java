package com.habitjournal.habit_journal_api.controller.mapper;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.controller.dto.HabitResponseDTO;
import com.habitjournal.habit_journal_api.model.Habit;
import com.habitjournal.habit_journal_api.model.LogEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface HabitMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(target = "id", ignore = true)
    Habit toHabit(HabitRequestDTO habitRequestDTO);

    default HabitResponseDTO toHabitDto(Habit habit) {
        if (habit == null) {
            return null;
        }
        List<LocalDateTime> logDates = habit.getLogEntries().stream()
                .map(LogEntry::getEntryDate).toList();

        System.out.println("logDates " + logDates);
        return new HabitResponseDTO(habit.getId(), habit.getName(), logDates);
    }
}

