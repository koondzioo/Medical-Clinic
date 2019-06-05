package com.app.medicalrestserver.controller;


import com.app.medicalrestserver.dto.DoctorDto;
import com.app.medicalrestserver.dto.PatientDto;
import com.app.medicalrestserver.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public DoctorDto addDoctor(@RequestBody DoctorDto doctorDto){
        return doctorService.addDoctort(doctorDto);
    }

    @GetMapping("/all")
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @DeleteMapping("/{id}")
    public DoctorDto deleteDoctor(@PathVariable Long id){
        return doctorService.deleteDoctor(id);
    }
}
