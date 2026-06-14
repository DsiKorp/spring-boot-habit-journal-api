package com.habitjournal.habit_journal_api.controller;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.controller.dto.HabitResponseDTO;
import com.habitjournal.habit_journal_api.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<HabitResponseDTO> createNewHabit(@Valid @RequestBody HabitRequestDTO habitRequestDTO) {
        return new ResponseEntity<>(habitService.createNewHabit(habitRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HabitResponseDTO>> findAllHabits() {
        return ResponseEntity.ok(habitService.findAllHabits());
    }

}
