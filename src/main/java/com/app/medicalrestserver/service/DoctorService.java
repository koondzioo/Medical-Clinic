package com.app.medicalrestserver.service;


import com.app.medicalrestserver.dto.DoctorDto;
import com.app.medicalrestserver.dto.mappers.ModelMapper;
import com.app.medicalrestserver.exceptions.MyException;
import com.app.medicalrestserver.model.Doctor;
import com.app.medicalrestserver.model.Specialization;
import com.app.medicalrestserver.model.Visit;
import com.app.medicalrestserver.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {


    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    private static Logger logger = LoggerFactory.getLogger(PatientService.class);


    public DoctorDto addDoctor(DoctorDto doctorDto) {
        try {
            if (doctorDto == null && doctorDto.getId() != null) {
                throw new NullPointerException("PATIENT OBJECT IS NOT CORRECT");
            }
            Doctor doctor1 = modelMapper.fromDoctorDtoToDoctor(doctorDto);
            doctorRepository.save(doctor1);
            return modelMapper.fromDoctorToDoctorDto(doctor1);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("CREATE DOCTOR");
        }
    }

    public List<DoctorDto> getAllDoctors() {
        try {
            return doctorRepository
                    .findAll()
                    .stream()
                    .map(modelMapper::fromDoctorToDoctorDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new MyException("GET ALL DOCTORS");
        }
    }


    public DoctorDto deleteDoctor(Long id) {
        try {
            Doctor doctor = doctorRepository.findById(id).orElseThrow(NullPointerException::new);
            doctorRepository.delete(doctor);
            return modelMapper.fromDoctorToDoctorDto(doctor);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("DELETE DOCTOR");
        }
    }

    public List<DoctorDto> getDoctorsBySpecialization(Specialization specialization) {
        try{
            return doctorRepository
                    .findAll()
                    .stream()
                    .filter(d -> d.getSpecialization().equals(specialization))
                    .map(modelMapper::fromDoctorToDoctorDto)
                    .collect(Collectors.toList());
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new MyException("GET LIST DOCTORS BY SPECIALIZATION");
        }
    }

    public DoctorDto findDoctorById(Long id){
        try{
            System.out.println("service" + doctorRepository.findById(id));
            return modelMapper.fromDoctorToDoctorDto(doctorRepository.findById(id).orElseThrow(NullPointerException::new));
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new MyException("FIND DOCTOR BY ID EXCEPTION");
        }
    }

    // TODO Update Doctor
    /*
    public DoctorDto updateDoctor(DoctorDto doctorDto) {
        try {
            Doctor doctor = modelMapper.fromDoctorDtoToDoctor(doctorDto);
            doctorRepository.save(doctor);
            return modelMapper.fromDoctorToDoctorDto(doctor);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("UPDATE DOCTOR EXCEPTION");
        }
    }

     */
}
