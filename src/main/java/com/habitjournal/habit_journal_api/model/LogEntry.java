package com.habitjournal.habit_journal_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString(exclude = "habit")
@Entity
@Table(name = "log_entries")
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime entryDate;

    // llave foranea, muchos registros pertenecen a 1 habito
    // terminado en One = fetch eager por default
    // terminado en many = fetch lazy por dafault
    @ManyToOne(fetch = FetchType.LAZY) // muchos LogEntry pertenecen a un habit
    @JoinColumn(name = "habit_id")
    private Habit habit;


}
