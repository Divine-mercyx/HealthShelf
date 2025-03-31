package org.healthShelfs.services;

import org.healthShelfs.data.models.appointment.Appointment;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.doctors.DoctorProfile;
import org.healthShelfs.data.models.users.MedicalHistory;
import org.healthShelfs.data.models.users.User;
import org.healthShelfs.data.repositories.*;
import org.healthShelfs.definedExceptions.DuplicateEmailException;
import org.healthShelfs.definedExceptions.DuplicateUsernameException;
import org.healthShelfs.definedExceptions.InvalidPasswordException;
import org.healthShelfs.definedExceptions.UserNotFoundException;
import org.healthShelfs.services.Dto.MedicalRecordRequest;
import org.healthShelfs.services.Dto.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    //67ea6e9b2db9130e88ceb407 doctor
    //67ea704c54dbad49345d5b1f user


    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorProfileRepository doctorProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void deleteAllDoctors() {
        doctorRepository.deleteAll();
    }

    public Doctor registerDoctor(Doctor doctor, DoctorProfile doctorProfile) {
        throwsDuplicateEmailException(doctor);
        throwsDuplicateUsernameException(doctor);
        DoctorProfile profile = doctorProfileRepository.save(doctorProfile);
        doctor.setDoctorProfile(profile);
        return doctorRepository.save(doctor);
    }

    public List<Appointment> getAppointments(String doctorId) {
        List<Appointment> appointments = appointmentRepository.findAll();
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().equals(doctor))
                .toList();
    }

    public Doctor findById(String id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Long count() {
        return doctorRepository.count();
    }

    public void deleteAccountById(String id) {
        doctorRepository.deleteById(id);
    }

    public Doctor login(UserLoginRequest request) {
        Doctor user = doctorRepository.findByEmail(request.getEmail());
        throwsUserNotFoundException(user);
        throwsInvalidPasswordException(user, request.getPassword());
        return user;
    }

    private void throwsDuplicateUsernameException(Doctor doctor) {
        if (doctorRepository.existsByUsername(doctor.getUsername())) throw new DuplicateUsernameException("username already in use");
    }

    private void throwsDuplicateEmailException(Doctor doctor) {
        if (doctorRepository.existsByEmail(doctor.getEmail())) throw new DuplicateEmailException("email already in use");
    }

    private void throwsUserNotFoundException(Doctor user) {
        if (user == null) throw new UserNotFoundException("invalid email or password");
    }

    private void throwsInvalidPasswordException(Doctor user, String password) {
        if (!user.getPassword().equals(password)) throw new InvalidPasswordException("invalid email or password");
    }

    public MedicalHistory createMedicalRecordForPatient(MedicalRecordRequest request) {
        User patient = userRepository.findById(request.getPatientId()).orElse(null);
        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);
        MedicalHistory medicalHistory = request.getMedicalHistory();
        medicalHistory.setPatient(patient);
        medicalHistory.setDoctor(doctor);
        return medicalHistoryRepository.save(medicalHistory);
    }

    public Doctor findBy(String username) {
        return doctorRepository.findByUsername(username);
    }

    public User findPatientBy(String username) {
        return userRepository.findByUsername(username);
    }
}
