package com.habitjournal.habit_journal_api.data;

import com.habitjournal.habit_journal_api.model.Habit;
import com.habitjournal.habit_journal_api.mongo_app.model.HabitMongo;
import com.habitjournal.habit_journal_api.mongo_app.model.LogEntryMongo;
import com.habitjournal.habit_journal_api.mongo_app.repository.HabitMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeederMongo implements CommandLineRunner {

    private final HabitMongoRepository habitRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Temporarily disable the dropCollection to see if the app starts
        // mongoTemplate.dropCollection(HabitMongo.class);

        // 1. Hábito Reciente y Completo
        createHabitWithLogs(
                "Aprender Arquitectura",
                // Logs de hoy y anteayer
                List.of(
                        LocalDateTime.now().minusDays(2),
                        LocalDateTime.now()
                )
        );

        // 2. Hábito con Actividad Media
        createHabitWithLogs(
                "Hacer Ejercicio (Yoga)",
                // Logs de hace 3 y 5 días
                List.of(
                        LocalDateTime.now().minusDays(5),
                        LocalDateTime.now().minusDays(3)
                )
        );

        // 3. Hábito Sin Registros (Para probar la exclusión en consultas)
        HabitMongo habit3 = new HabitMongo();
        habit3.setName("Beber Agua (Inactivo)");
        habitRepository.save(habit3);


        System.out.println("✅ Datos de prueba cargados: 3 Hábitos.");

    }

    // --- MÉTODO AUXILIAR PARA GESTIONAR LA CREACIÓN Y VINCULACIÓN ---
    private void createHabitWithLogs(String name, List<LocalDateTime> dates) {
        HabitMongo habit = new HabitMongo();
        habit.setName(name);

        // Mapea la lista de fechas a objetos LogEntry y asegura la vinculación bidireccional
        List<LogEntryMongo> logs = dates.stream()
                .map(date -> {
                    LogEntryMongo log = new LogEntryMongo();
                    log.setEntryDate(date);
                    return log;
                })
                .toList();

        // Asigna la lista de logs al padre (el cascade se encarga de guardar)
        habit.getLogEntries().addAll(logs);

        // Guardamos el padre
        habitRepository.save(habit);
    }
}
