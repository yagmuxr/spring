package dev.danvega.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    //Koşu kayıtlarını saklamak ve yönetmek
    private List<Run> runs = new ArrayList<>();
    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) { //may or may not contain a non null value
        return runs.stream().filter(run -> run.id() == id).findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(Integer id) {
        runs.removeIf(run -> run.id() == id);
    }
    @PostConstruct //used on a method that needs to be executed after dependency injection is done
    private void init() {
        runs.add(
                new Run(1, "Morning Run", LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1).plusHours(1), 5, Location.OUTDOOR)
        );
        runs.add(
                new Run(2, "Evening Run", LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(2).plusHours(1), 4, Location.INDOOR)
        );
        runs.add(
                new Run(3, "Afternoon Run", LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(3).plusHours(1), 6, Location.OUTDOOR)
        );
    }
}

