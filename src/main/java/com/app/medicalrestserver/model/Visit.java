package com.app.medicalrestserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Visit {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "doctor_id" )
    private Doctor doctor;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_id" )
    private Patient patient;
    private String description;
}
