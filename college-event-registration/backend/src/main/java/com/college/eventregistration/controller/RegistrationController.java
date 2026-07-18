package com.college.eventregistration.controller;

import com.college.eventregistration.dto.RegistrationRequest;
import com.college.eventregistration.model.Event;
import com.college.eventregistration.model.Registration;
import com.college.eventregistration.repository.EventRepository;
import com.college.eventregistration.repository.RegistrationRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "http://localhost:5173")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    public RegistrationController(
            RegistrationRepository registrationRepository,
            EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Registration registration = new Registration();
        registration.setStudentName(request.getStudentName());
        registration.setEmail(request.getEmail());
        registration.setDepartment(request.getDepartment());
        registration.setYear(request.getYear());
        registration.setEvent(event);

        return ResponseEntity.ok(registrationRepository.save(registration));
    }

    @GetMapping("/event/{eventId}")
    public List<Registration> getParticipants(@PathVariable Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }
}
