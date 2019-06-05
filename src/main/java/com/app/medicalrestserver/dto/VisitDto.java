package com.app.medicalrestserver.dto;


import com.app.medicalrestserver.model.Doctor;
import com.app.medicalrestserver.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitDto {

    private Long id;
    private LocalDate date;
    private Doctor doctor;
    private Patient patient;
    private String description;
}
