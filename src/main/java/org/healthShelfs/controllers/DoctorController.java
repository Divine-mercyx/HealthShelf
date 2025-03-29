package org.healthShelfs.controllers;

import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.services.DoctorService;
import org.healthShelfs.services.Dto.DoctorRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register")
    public Doctor registerDoctor(@RequestBody DoctorRegistrationRequest request) {
        return doctorService.registerDoctor(request.getDoctor(), request.getDoctorProfile());
    }
}
