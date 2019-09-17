package com.app.medicalrestserver.service;

import com.app.medicalrestserver.dto.VisitDto;
import com.app.medicalrestserver.dto.mappers.ModelMapper;
import com.app.medicalrestserver.exceptions.MyException;
import com.app.medicalrestserver.model.Doctor;
import com.app.medicalrestserver.model.Patient;
import com.app.medicalrestserver.model.Visit;
import com.app.medicalrestserver.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class VisitService {

    private VisitRepository visitRepository;
    private PatientService patientService;
    private DoctorService doctorService;
    private ModelMapper modelMapper;
    private static Logger logger = LoggerFactory.getLogger(VisitService.class);


    @Autowired
    public VisitService(VisitRepository visitRepository, PatientService patientService, DoctorService doctorService, ModelMapper modelMapper) {
        this.visitRepository = visitRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    public VisitDto addVisit(VisitDto visitDto) {
        try {
            if (visitDto == null && visitDto.getId() != null) {
                throw new NullPointerException("VISIT OBJECT IS NOT CORRECT");
            }
            Doctor doctor = modelMapper.fromDoctorDtoToDoctor(doctorService.findDoctorById(visitDto.getDoctorId()));
            Patient patient = modelMapper.fromPatientDtoToPatient(patientService.findPatientById(visitDto.getPatientId()));
            Visit visit = modelMapper.fromVisitDtoToVisit(visitDto);
            visit.setDoctor(doctor);
            visit.setPatient(patient);
            visitRepository.save(visit);
            return modelMapper.fromVisitToVisitDto(visit);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new MyException("CREATE VISIT EXCEPTION");
        }

    }

    public VisitDto deleteVisit(Long id) {
        try {
            Visit visit = visitRepository.findById(id).orElseThrow(NullPointerException::new);
            visitRepository.delete(visit);
            return modelMapper.fromVisitToVisitDto(visit);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("DELETE VISIT EXCEPTION");
        }
    }

    public List<VisitDto> getVisitsByPatient(Long id) {
        try {
            Patient patient = modelMapper.fromPatientDtoToPatient(patientService.findPatientById(id));
            return patient.getVisitList().stream().map(modelMapper::fromVisitToVisitDto).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new MyException("GET VISITS BY PATIENT");
        }
    }

    public VisitDto getVisitById(Long id) {
        try {
            return modelMapper.fromVisitToVisitDto(visitRepository.findById(id).orElseThrow(NullPointerException::new));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("GET VISIT BY ID");
        }
    }
}
