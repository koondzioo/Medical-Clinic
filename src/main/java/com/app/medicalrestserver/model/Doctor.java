package com.app.medicalrestserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Doctor {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String login;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private String password;
    @Transient
    private String passwordConfirmation;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "doctor")
    private List<Visit> visitList;

}
