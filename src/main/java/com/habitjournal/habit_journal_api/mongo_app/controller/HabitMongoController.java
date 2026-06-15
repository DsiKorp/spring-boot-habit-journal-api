package com.habitjournal.habit_journal_api.mongo_app.controller;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.mongo_app.controller.dto.HabitMResponseDTO;
import com.habitjournal.habit_journal_api.mongo_app.service.HabitMongoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/mongo/habits")
@RequiredArgsConstructor
public class HabitMongoController {

    private final HabitMongoService habitService;

    @PostMapping
    public ResponseEntity<HabitMResponseDTO> createNewHabit(@Valid @RequestBody HabitRequestDTO habitRequestDTO) {
        return new ResponseEntity<>(habitService.createNewHabit(habitRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HabitMResponseDTO>> findAllHabits() {
        return ResponseEntity.ok(habitService.findAllHabits());
    }

    @GetMapping("/active")
    public ResponseEntity<List<HabitMResponseDTO>> getActiveHabits(@RequestParam(defaultValue = "7") int days) {
        return ResponseEntity.ok(habitService.findHabitsLoggedSince(days));
    }

}
