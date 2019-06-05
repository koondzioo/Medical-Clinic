package com.app.medicalrestserver.dto.mappers;

import com.app.medicalrestserver.dto.DoctorDto;
import com.app.medicalrestserver.dto.PatientDto;
import com.app.medicalrestserver.dto.VisitDto;
import com.app.medicalrestserver.model.Doctor;
import com.app.medicalrestserver.model.Patient;
import com.app.medicalrestserver.model.Visit;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public PatientDto fromPatientToPatientDto(Patient patient) {
        return patient == null ? null : PatientDto.builder()
                .id(patient.getId())
                .age(patient.getAge())
                .email(patient.getEmail())
                .name(patient.getName())
                .surname(patient.getSurname())
                .login(patient.getLogin())
                .password(patient.getPassword())
                .visitList(patient.getVisitList())
                .build();
    }


    public Patient fromPatientDtoToPatient(PatientDto patientDto) {
        return patientDto == null ? null : Patient.builder()
                .id(patientDto.getId())
                .age(patientDto.getAge())
                .email(patientDto.getEmail())
                .name(patientDto.getName())
                .surname(patientDto.getSurname())
                .login(patientDto.getLogin())
                .password(patientDto.getPassword())
                .visitList(patientDto.getVisitList())
                .build();
    }

    public DoctorDto fromDoctorToDoctorDto(Doctor doctor) {
        return doctor == null ? null : DoctorDto.builder()
                .id(doctor.getId())
                .email(doctor.getEmail())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .login(doctor.getPassword())
                .specialization(doctor.getSpecialization())
                .visitList(doctor.getVisitList())
                .password(doctor.getPassword())
                .build();
    }

    public Doctor fromDoctorDtoToDoctor(DoctorDto doctorDto) {
        return doctorDto == null ? null : Doctor.builder()
                .id(doctorDto.getId())
                .email(doctorDto.getEmail())
                .name(doctorDto.getName())
                .surname(doctorDto.getSurname())
                .login(doctorDto.getPassword())
                .specialization(doctorDto.getSpecialization())
                .visitList(doctorDto.getVisitList())
                .password(doctorDto.getPassword())
                .build();
    }

    public VisitDto fromVisitToVisitDto(Visit visit){
        return visit == null ? null : VisitDto.builder()
                .date(visit.getDate())
                .description(visit.getDescription())
                .doctor(visit.getDoctor())
                .patient(visit.getPatient())
                .id(visit.getId())
                .build();
    }

    public Visit fromVisitDtoToVisit(VisitDto visitDto){
        return visitDto == null ? null : Visit.builder()
                .date(visitDto.getDate())
                .description(visitDto.getDescription())
                .doctor(visitDto.getDoctor())
                .patient(visitDto.getPatient())
                .id(visitDto.getId())
                .build();
    }
}
