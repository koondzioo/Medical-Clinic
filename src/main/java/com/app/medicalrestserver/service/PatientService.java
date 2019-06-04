package com.app.medicalrestserver.service;


import com.app.medicalrestserver.exceptions.MyException;
import com.app.medicalrestserver.model.Patient;
import com.app.medicalrestserver.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private static Logger logger = LoggerFactory.getLogger(PatientService.class);


    public Patient addPatient(Patient patient) {
        Patient patient1 = null;
        if(patient == null && patient.getId() != null){
            throw new NullPointerException("PATIENT OBJECT IS NOT CORRECT");
        }
        try {
                return patientRepository.save(patient);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("CREATE PATIENT EXCEPTION");
        }
    }

}
