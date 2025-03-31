package org.healthShelfs.controllers;

import org.healthShelfs.data.models.appointment.Appointment;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.users.MedicalHistory;
import org.healthShelfs.data.models.users.User;
import org.healthShelfs.services.DoctorService;
import org.healthShelfs.services.Dto.DoctorRegistrationRequest;
import org.healthShelfs.services.Dto.MedicalRecordRequest;
import org.healthShelfs.services.Dto.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register")
    public Doctor registerDoctor(@RequestBody DoctorRegistrationRequest request) {
        return doctorService.registerDoctor(request.getDoctor(), request.getDoctorProfile());
    }

    @PostMapping("/login")
    public Doctor loginDoctor(@RequestBody UserLoginRequest request) {
        return doctorService.login(request);
    }

    @PostMapping("/medical/records")
    public MedicalHistory createMedicalRecordForPatient(@RequestBody MedicalRecordRequest request) {
        return doctorService.createMedicalRecordForPatient(request);
    }

    @GetMapping("/{id}")
    public Doctor findUserById(@PathVariable String id) {
        return doctorService.findById(id);
    }

    @GetMapping("/")
    public Doctor findDoctorsByUsername(@RequestParam String username) {
        return doctorService.findBy(username.trim());
    }

    @GetMapping("/patients")
    public User findPatientsByUsername(@RequestParam String username) {
        return doctorService.findPatientBy(username.trim());
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable String id) {
        doctorService.deleteAccountById(id);
        return "account deleted";
    }

    @GetMapping("/appointments/record/{userId}")
    public List<Appointment> getAppointments(@PathVariable String userId) {
        return doctorService.getAppointments(userId);
    }
}
