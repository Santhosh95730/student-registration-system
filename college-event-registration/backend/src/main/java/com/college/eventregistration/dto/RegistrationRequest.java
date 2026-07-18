package com.college.eventregistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotBlank
    private String studentName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String department;

    @NotBlank
    private String year;

    @NotNull
    private Long eventId;
}
