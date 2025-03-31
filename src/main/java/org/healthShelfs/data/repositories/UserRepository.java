package org.healthShelfs.data.repositories;

import org.healthShelfs.data.models.users.MedicalHistory;
import org.healthShelfs.data.models.users.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);
    User findByUsername(String username);
    User findByEmail(String email);
    boolean existsByUsername(String username);
}
