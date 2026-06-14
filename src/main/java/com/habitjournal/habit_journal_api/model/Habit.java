package com.habitjournal.habit_journal_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "logEntries")
@Entity
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // a nivel logico de la app, no la db, no hay logEntries en la db
    // un habit puede tener muchos logEntry
    // nombre habit declarado en LogEntry.java
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    List<LogEntry> logEntries = new ArrayList<>();
}
