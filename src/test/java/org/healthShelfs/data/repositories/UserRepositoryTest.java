package org.healthShelfs.data.repositories;

import org.healthShelfs.data.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanUpBefore() {
        userRepository.deleteAll();
    }

    @AfterEach
    void cleanUpAfter() {
        userRepository.deleteAll();
    }


    @Test
    public void saveUser_countUser() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@gmail.com");
        user.setPassword("password");

        userRepository.save(user);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void saveUser_findUserById() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        User user1 = userRepository.save(user);
        assertNotNull(user1);
        Optional<User> user2 = userRepository.findById(user1.getId());
        assertTrue(user2.isPresent());
        assertEquals(user1, user2.get());
    }

    @Test
    public void saveUser_checkIfUserExistsById() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(savedUser.getId(), user.getId());
        assertTrue(userRepository.existsById(savedUser.getId()));
    }

    @Test
    public void saveUser_checkIfUserExists() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(savedUser.getId(), user.getId());
        assertTrue(userRepository.existsByEmail(user.getEmail()));
    }

    @Test
    public void saveUser_deleteUserById() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(userRepository.count(), 1L);
        userRepository.deleteById(savedUser.getId());
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveUser_deleteUser() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(userRepository.count(), 1L);
        userRepository.delete(savedUser);
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveUsers_deleteUser() {
        User user1 = new User();
        user1.setUsername("username");
        user1.setEmail("email@gmail.com");
        user1.setPassword("password");
        User savedUser = userRepository.save(user1);
        User user2 = new User();
        user2.setUsername("username2");
        user2.setEmail("email2@gmail.com");
        user2.setPassword("password2");
        User savedUser2 = userRepository.save(user2);
        assertNotNull(savedUser);
        assertNotNull(savedUser2);
        assertEquals(userRepository.count(), 2L);
        userRepository.deleteAll();
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveUsers_deleteUsersById() {
        User user1 = new User();
        user1.setUsername("username");
        user1.setEmail("email@gmail.com");
        user1.setPassword("password");
        User savedUser = userRepository.save(user1);
        User user2 = new User();
        user2.setUsername("username2");
        user2.setEmail("email2@gmail.com");
        user2.setPassword("password2");
        User savedUser2 = userRepository.save(user2);
        assertNotNull(savedUser);
        assertNotNull(savedUser2);
        assertEquals(userRepository.count(), 2L);
        List<String> usersId = new ArrayList<>();
        usersId.add(savedUser.getId());
        usersId.add(savedUser2.getId());
        userRepository.deleteAllById(usersId);
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveUsers_findUsersById() {
        User user1 = new User();
        user1.setUsername("username");
        user1.setEmail("email@gmail.com");
        user1.setPassword("password");
        User savedUser = userRepository.save(user1);
        User user2 = new User();
        user2.setUsername("username2");
        user2.setEmail("email2@gmail.com");
        user2.setPassword("password2");
        User savedUser2 = userRepository.save(user2);
        assertNotNull(savedUser);
        assertNotNull(savedUser2);
        assertEquals(userRepository.count(), 2L);
        List<String> usersId = new ArrayList<>();
        usersId.add(savedUser.getId());
        usersId.add(savedUser2.getId());
        List<User> foundUsers = userRepository.findAllById(usersId);
        assertEquals(2, foundUsers.size());
    }

    @Test
    public void saveUser_findUserByUsername() {
        User user1 = new User();
        user1.setUsername("username");
        user1.setEmail("email@gmail.com");
        user1.setPassword("password");
        User savedUser = userRepository.save(user1);
        List<User> foundUser = userRepository.findByUsername(user1.getUsername());
        assertEquals(foundUser.get(0), savedUser);
        assertEquals(foundUser.get(0).getId(), savedUser.getId());
    }
}