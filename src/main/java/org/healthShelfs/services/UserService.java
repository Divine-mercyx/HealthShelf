package org.healthShelfs.services;

import org.healthShelfs.data.models.appointment.Appointment;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.users.MedicalHistory;
import org.healthShelfs.data.models.users.User;
import org.healthShelfs.data.models.users.UserProfile;
import org.healthShelfs.data.repositories.*;
import org.healthShelfs.definedExceptions.DuplicateEmailException;
import org.healthShelfs.definedExceptions.DuplicateUsernameException;
import org.healthShelfs.definedExceptions.InvalidPasswordException;
import org.healthShelfs.definedExceptions.UserNotFoundException;
import org.healthShelfs.services.Dto.UserAppointmentRequest;
import org.healthShelfs.services.Dto.UserLoginRequest;
import org.healthShelfs.services.Dto.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;



    public void deleteAll() {
        userRepository.deleteAll();
    }

    public User registerUser(UserRegistrationRequest request) {
        throwsDuplicateEmailException(request.getUser());
        throwsDuplicateUsernameException(request.getUser());
        UserProfile savedProfile = profileRepository.save(request.getProfile());
        request.getUser().setProfile(savedProfile);
        return userRepository.save(request.getUser());
    }

    public void deleteAccountById(String id) {
        userRepository.deleteById(id);
    }

    public List<MedicalHistory> getMedicalHistoriesById(String id) {
        User user = userRepository.findById(id).orElse(null);
        return medicalHistoryRepository.findAll().stream()
                .filter(medicalHistory -> medicalHistory.getPatient().getId().equals(id))
                .toList();
    }

    public User login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        throwsUserNotFoundException(user);
        throwsInvalidPasswordException(user, request.getPassword());
        return user;
    }

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAnAppointment(UserAppointmentRequest request) {
        Optional<User> patient = userRepository.findById(request.getUser().getId());
        Optional<Doctor> doctor = doctorRepository.findById(request.getDoctor().getId());
        patient.ifPresent(request.getAppointment()::setPatient);
        doctor.ifPresent(request.getAppointment()::setDoctor);
        return appointmentRepository.save(request.getAppointment());
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Long count() {
        return userRepository.count();
    }

    public Doctor findDoctorsByUser(String username) {
        return doctorRepository.findByUsername(username);
    }

    public List<Appointment> getAppointments(String userId) {
        List<Appointment> appointments = appointmentRepository.findAll();
        User patient = userRepository.findById(userId).orElse(null);
        return appointments.stream()
                .filter(appointment -> appointment.getPatient().equals(patient))
                .toList();
    }

    private void throwsDuplicateUsernameException(User user) {
        if (userRepository.existsByUsername(user.getUsername())) throw new DuplicateUsernameException("username already in use");
    }


    private void throwsUserNotFoundException(User user) {
        if (user == null) throw new UserNotFoundException("invalid email or password");
    }

    private void throwsInvalidPasswordException(User user, String password) {
        if (!user.getPassword().equals(password)) throw new InvalidPasswordException("invalid email or password");
    }

    private void throwsDuplicateEmailException(User user) {
        if (userRepository.existsByEmail(user.getEmail())) throw new DuplicateEmailException("email already in use");
    }
}
