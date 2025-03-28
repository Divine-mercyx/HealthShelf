package org.healthShelfs.services;

import org.healthShelfs.data.models.User;
import org.healthShelfs.data.models.UserProfile;
import org.healthShelfs.data.repositories.UserRepository;
import org.healthShelfs.definedExceptions.DuplicateEmailException;
import org.healthShelfs.definedExceptions.InvalidPasswordException;
import org.healthShelfs.definedExceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void deleteAll() {
        userRepository.deleteAll();
    }

    public User registerUser(User user, UserProfile profile) {
        throwsDuplicateEmailException(user);
        user.setProfileId(profile.getId());
        return userRepository.save(user);
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

    public void deleteAccountById(String id) {
        userRepository.deleteAllById(Collections.singleton(id));
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        throwsUserNotFoundException(user);
        throwsInvalidPasswordException(user, password);
        return user;
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
