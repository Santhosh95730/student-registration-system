package com.college.eventregistration.config;

import com.college.eventregistration.model.Event;
import com.college.eventregistration.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadData(EventRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Event(
                        null,
                        "Tech Fest 2026",
                        "Technical competitions and workshops",
                        "2026-08-10",
                        "Main Auditorium",
                        "CSE Department"
                ));

                repository.save(new Event(
                        null,
                        "Cultural Fest",
                        "Music, dance and cultural programs",
                        "2026-08-20",
                        "College Ground",
                        "Student Activities Club"
                ));

                repository.save(new Event(
                        null,
                        "Sports Meet",
                        "Inter-department sports competitions",
                        "2026-09-05",
                        "Sports Ground",
                        "Physical Education Department"
                ));
            }
        };
    }
}
