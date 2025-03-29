package org.healthShelfs.controllers;

import org.healthShelfs.data.models.appointment.Appointment;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.users.User;
import org.healthShelfs.services.Dto.UserAppointmentRequest;
import org.healthShelfs.services.Dto.UserRegistrationRequest;
import org.healthShelfs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    //67e6dca3d4a2dd6ffd218d27

    //67e791fecde6504376b9e2a3

    //67e79347cde6504376b9e2a4

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegistrationRequest request) {
        return userService.registerUser(request.getUser(), request.getProfile());
    }

    @PostMapping("/create/appointment")
    public Appointment createAppointment(@RequestBody UserAppointmentRequest request) {
        return userService.createAnAppointment(request.getAppointment(), request.getUser().getId(), request.getDoctor().getId());
    }

    @GetMapping("/get/appointments/record/{userId}")
    public List<Appointment> getAppointments(@PathVariable String userId) {
        return userService.getAppointments(userId);
    }

    @GetMapping("/get/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/get/users")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/find/doctors")
    public List<Doctor> findDoctorsByUsername(@RequestParam String username) {
        return userService.findDoctorsByUser(username.trim());
    }
}
