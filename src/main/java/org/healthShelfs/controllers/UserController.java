package org.healthShelfs.controllers;

import org.healthShelfs.data.models.appointment.Appointment;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.users.MedicalHistory;
import org.healthShelfs.data.models.users.User;
import org.healthShelfs.services.Dto.UserAppointmentRequest;
import org.healthShelfs.services.Dto.UserLoginRequest;
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
        return userService.registerUser(request);
    }

    @GetMapping("/medical/history/{id}")
    public List<MedicalHistory> getMedicalHistory(@PathVariable String id) {
        return userService.getMedicalHistoriesById(id);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/appointment")
    public Appointment createAppointment(@RequestBody UserAppointmentRequest request) {
        return userService.createAnAppointment(request);
    }

    @GetMapping("/appointments/record/{userId}")
    public List<Appointment> getAppointments(@PathVariable String userId) {
        return userService.getAppointments(userId);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/patients")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/doctors")
    public Doctor findDoctorsByUsername(@RequestParam String username) {
        return userService.findDoctorsByUser(username.trim());
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable String id) {
        userService.deleteAccountById(id);
        return "account deleted";
    }
}
