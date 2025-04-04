package org.healthShelfs.data.repositories;

import org.healthShelfs.data.models.users.UserProfile;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<UserProfile, String> {

}
