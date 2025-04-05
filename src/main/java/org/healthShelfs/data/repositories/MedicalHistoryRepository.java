package org.healthShelfs.data.repositories;

import org.healthShelfs.data.models.users.MedicalHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory, String> {
}
