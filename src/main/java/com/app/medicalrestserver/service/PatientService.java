package com.app.medicalrestserver.service;


import com.app.medicalrestserver.dto.PatientDto;
import com.app.medicalrestserver.dto.mappers.ModelMapper;
import com.app.medicalrestserver.exceptions.MyException;
import com.app.medicalrestserver.model.Patient;
import com.app.medicalrestserver.model.Visit;
import com.app.medicalrestserver.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PatientService {

    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    private static Logger logger = LoggerFactory.getLogger(PatientService.class);


    public PatientDto addPatient(PatientDto patientDto) {
        try {
            if (patientDto == null && patientDto.getId() != null) {
                throw new NullPointerException("PATIENT OBJECT IS NOT CORRECT");
            }
            Patient patient1 = modelMapper.fromPatientDtoToPatient(patientDto);
            patientRepository.save(patient1);
            return modelMapper.fromPatientToPatientDto(patient1);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("CREATE PATIENT EXCEPTION");
        }
    }

    public List<PatientDto> getAllPatients() {
        try {
            return patientRepository
                    .findAll()
                    .stream()
                    .map(modelMapper::fromPatientToPatientDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("GET ALL PATIENTS");
        }
    }


    public PatientDto deletePatient(Long id) {
        try {
            Patient patient = patientRepository.findById(id).orElseThrow(NullPointerException::new);
            patientRepository.delete(patient);
            return modelMapper.fromPatientToPatientDto(patient);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("DELETE PATIENT");
        }
    }

    public PatientDto findPatientById(Long id){
        try {
            return modelMapper.fromPatientToPatientDto(patientRepository.findById(id).orElseThrow(NullPointerException::new));
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("FIND PATIENT BY ID");
        }
    }
}
