package org.healthShelfs.services;

import org.healthShelfs.data.models.appointment.Appointment;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.users.User;
import org.healthShelfs.data.models.users.UserProfile;
import org.healthShelfs.data.repositories.AppointmentRepository;
import org.healthShelfs.data.repositories.DoctorRepository;
import org.healthShelfs.data.repositories.ProfileRepository;
import org.healthShelfs.data.repositories.UserRepository;
import org.healthShelfs.definedExceptions.DuplicateEmailException;
import org.healthShelfs.definedExceptions.InvalidPasswordException;
import org.healthShelfs.definedExceptions.UserNotFoundException;
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



    public void deleteAll() {
        userRepository.deleteAll();
    }

    public User registerUser(User user, UserProfile profile) {
        throwsDuplicateEmailException(user);
        UserProfile savedProfile = profileRepository.save(profile);
        user.setProfile(savedProfile);
        return userRepository.save(user);
    }

    public void deleteAccountById(String id) {
        userRepository.deleteById(id);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        throwsUserNotFoundException(user);
        throwsInvalidPasswordException(user, password);
        return user;
    }

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAnAppointment(Appointment appointment, String patientId, String doctorId) {
        Optional<User> patient = userRepository.findById(patientId);
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        patient.ifPresent(appointment::setPatient);
        doctor.ifPresent(appointment::setDoctor);
        return appointmentRepository.save(appointment);
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

    public List<Doctor> findDoctorsByUser(String username) {
        return doctorRepository.findByUsername(username);
    }

    public List<Appointment> getAppointments(String userId) {
        List<Appointment> appointments = appointmentRepository.findAll();
        User patient = userRepository.findById(userId).orElse(null);
        return appointments.stream()
                .filter(appointment -> appointment.getPatient().equals(patient))
                .toList();
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
