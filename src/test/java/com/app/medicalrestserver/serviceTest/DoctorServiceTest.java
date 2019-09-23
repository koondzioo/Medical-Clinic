package com.app.medicalrestserver.serviceTest;

import com.app.medicalrestserver.dto.DoctorDto;
import com.app.medicalrestserver.dto.VisitDto;
import com.app.medicalrestserver.dto.mappers.ModelMapper;
import com.app.medicalrestserver.model.Doctor;
import com.app.medicalrestserver.model.Specialization;
import com.app.medicalrestserver.model.Visit;
import com.app.medicalrestserver.repository.DoctorRepository;
import com.app.medicalrestserver.service.DoctorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
public class DoctorServiceTest {

    @TestConfiguration
    public static class DoctorServiceConfiguration {

        @MockBean
        public DoctorRepository doctorRepository;

        @MockBean
        public ModelMapper modelMapper;

        @Bean
        public DoctorService doctorService() {
            return new DoctorService(doctorRepository, modelMapper);
        }

    }

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void getAllDoctors() {

        Mockito
                .when(doctorRepository.findAll())
                .thenReturn(List.of(
                        Doctor.builder()
                                .login("Doctor1")
                                .name("Doctor1")
                                .surname("Doctor1")
                                .email("Email1@email.com")
                                .password("123")
                                .specialization(Specialization.NEUROLOGY)
                                .build(),
                        Doctor.builder()
                                .login("Doctor2")
                                .name("Doctor2")
                                .surname("Doctor2")
                                .email("Email2@email.com")
                                .password("123")
                                .specialization(Specialization.ANESTHESIOLOGY)
                                .build()
                ));

        var doctors = doctorService.getAllDoctors();

        doctors.stream().forEach(System.out::println);

        Assertions.assertEquals(2, doctors.size());

    }

    @Test
    public void getDoctorById() {

        var doctorFromRepo = Doctor.builder()
                .id(1L)
                .login("Doctor1")
                .name("Doctor1")
                .surname("Doctor1")
                .email("Email1@email.com")
                .password("123")
                .passwordConfirmation("123")
                .visitList(new HashSet<>())
                .specialization(Specialization.NEUROLOGY)
                .build();

        System.out.println("------Optional from REPO--------");
        System.out.println(Optional.of(doctorFromRepo));

        Mockito
                .when(doctorRepository.findById(1L))
                .thenReturn(Optional.of(doctorFromRepo));

        System.out.println(doctorService.findDoctorById(1L));

        var doctor = doctorService.findDoctorById(1L);
        Assertions.assertEquals("Doctor1", doctor.getName());
        Assertions.assertEquals("Doctor1", doctor.getSurname());
        Assertions.assertEquals("Doctor1", doctor.getLogin());
    }

}
