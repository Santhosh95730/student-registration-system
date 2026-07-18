package com.college.eventregistration.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "registrations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String email;
    private String department;
    private String year;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
