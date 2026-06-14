package com.habitjournal.habit_journal_api.service;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.controller.dto.HabitResponseDTO;
import com.habitjournal.habit_journal_api.controller.mapper.HabitMapper;
import com.habitjournal.habit_journal_api.model.Habit;
import com.habitjournal.habit_journal_api.model.LogEntry;
import com.habitjournal.habit_journal_api.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final HabitMapper habitMapper;

    @Override
    @Transactional
    public HabitResponseDTO createNewHabit(HabitRequestDTO habitRequestDTO) {

//        habitRepository.findByName(habitRequestDTO.name()).ifPresent(habit -> {
//            throw new DuplicateHabitException(habitRequestDTO.name());
//        });

        Optional<Habit> existingHabitOptional = habitRepository.findByName(habitRequestDTO.name());
        Habit habitDb = existingHabitOptional.orElseGet(() -> habitMapper.toHabit(habitRequestDTO));

        if (habitRequestDTO.logs() != null && !habitRequestDTO.logs().isEmpty()) {
            for (LocalDateTime date : habitRequestDTO.logs()) {

                LogEntry log = new LogEntry();
                log.setEntryDate(date);
                log.setHabit(habitDb); //Configura FK para JPA

                habitDb.getLogEntries().add(log);
            }
        }

        return habitMapper.toHabitDto(habitRepository.save(habitDb));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitResponseDTO> findAllHabits() {
        return habitRepository.findAllWithLogs().stream()
                .map(habitMapper::toHabitDto)
                .toList();
    }
}
