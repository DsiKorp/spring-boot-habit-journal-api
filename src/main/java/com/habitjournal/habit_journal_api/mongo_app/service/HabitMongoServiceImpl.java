package com.habitjournal.habit_journal_api.mongo_app.service;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.mongo_app.controller.dto.HabitMResponseDTO;
import com.habitjournal.habit_journal_api.mongo_app.controller.mapper.HabitMongoMapper;
import com.habitjournal.habit_journal_api.mongo_app.model.HabitMongo;
import com.habitjournal.habit_journal_api.mongo_app.model.LogEntryMongo;
import com.habitjournal.habit_journal_api.mongo_app.repository.HabitMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitMongoServiceImpl implements HabitMongoService {
    private final HabitMongoRepository habitMongoRepository;
    private final HabitMongoMapper habitMongoMapper;

    @Override
    @Transactional
    public HabitMResponseDTO createNewHabit(HabitRequestDTO requestDTO) {

//        habitMongoRepository.findByName(requestDTO.name()).ifPresent(habit -> {
//            throw new DuplicateHabitException(requestDTO.name());
//        });

        Optional<HabitMongo> existingHabitOptional = habitMongoRepository.findByName(requestDTO.name());
        HabitMongo habitDb = existingHabitOptional.orElseGet(() -> habitMongoMapper.toHabitMongo(requestDTO));


        if (requestDTO.logs() != null && !requestDTO.logs().isEmpty()) {

            for (LocalDateTime date : requestDTO.logs()) {

                LogEntryMongo log = new LogEntryMongo();
                log.setEntryDate(date);

                habitDb.getLogEntries().add(log);
            }
        }

        return mapToResponseDTO(habitMongoRepository.save(habitDb));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitMResponseDTO> findAllHabits() {
        List<HabitMongo> habits = habitMongoRepository.findAll();

        return habits.stream().map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitMResponseDTO> findHabitsLoggedSince(int days) {

        LocalDateTime sinceDate = LocalDateTime.now().minusDays(days);

        List<HabitMongo> activeHabits = habitMongoRepository.findHabitsLoggedSince(sinceDate);

        return activeHabits.stream().map(this::mapToResponseDTO).toList();
    }

    private HabitMResponseDTO mapToResponseDTO(HabitMongo habit) {
        List<LocalDateTime> logDates = habit.getLogEntries().stream()
                .map(LogEntryMongo::getEntryDate).toList();

        return new HabitMResponseDTO(habit.getId(), habit.getName(), logDates);
    }

}












