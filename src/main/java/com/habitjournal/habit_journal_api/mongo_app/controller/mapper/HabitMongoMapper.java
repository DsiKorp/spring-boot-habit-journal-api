package com.habitjournal.habit_journal_api.mongo_app.controller.mapper;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.mongo_app.controller.dto.HabitMResponseDTO;
import com.habitjournal.habit_journal_api.mongo_app.model.HabitMongo;
import com.habitjournal.habit_journal_api.mongo_app.model.LogEntryMongo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface HabitMongoMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "logEntries", ignore = true)
    HabitMongo toHabitMongo(HabitRequestDTO habitRequestDTO);

    default HabitMResponseDTO toHabitMongoDto(HabitMongo habitMongo) {
        if (habitMongo == null) {
            return null;
        }
        List<LocalDateTime> logDates = habitMongo.getLogEntries().stream()
                .map(LogEntryMongo::getEntryDate).toList();

        System.out.println("logDates " + logDates);
        return new HabitMResponseDTO(habitMongo.getId(), habitMongo.getName(), logDates);
    }
}

