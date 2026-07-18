package com.college.eventregistration.repository;

import com.college.eventregistration.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
