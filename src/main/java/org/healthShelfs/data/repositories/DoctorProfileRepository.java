package org.healthShelfs.data.repositories;

import org.healthShelfs.data.models.doctors.DoctorProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorProfileRepository extends MongoRepository<DoctorProfile, String> {
}
