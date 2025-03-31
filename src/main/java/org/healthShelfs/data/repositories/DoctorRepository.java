package org.healthShelfs.data.repositories;

import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {

    boolean existsByEmail(String email);
    Doctor findByUsername(String username);
    Doctor findByEmail(String email);
    boolean existsByUsername(String username);
}
