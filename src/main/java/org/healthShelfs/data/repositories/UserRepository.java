package org.healthShelfs.data.repositories;

import org.healthShelfs.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);
    List<User> findByUsername(String username);
    User findByEmail(String email);
}
