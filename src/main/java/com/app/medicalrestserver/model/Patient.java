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
public class Patient {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private Integer age;
    private String password;
    @Transient
    private String passwordConfirmation;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "patient")
    private List<Visit> visitList;

}
