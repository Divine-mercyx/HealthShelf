package org.healthShelfs.services;

import org.healthShelfs.data.models.users.User;
import org.healthShelfs.data.models.users.UserProfile;
import org.healthShelfs.definedExceptions.DuplicateEmailException;
import org.healthShelfs.definedExceptions.InvalidPasswordException;
import org.healthShelfs.definedExceptions.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    private UserProfile profile;

    @BeforeEach
    void setUp() {
        profile = new UserProfile();
        userService.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }

    @Test
    public void saveUser_TestUserService() {
        User user = new User();
        user.setUsername("john");
        user.setPassword("password");
        user.setEmail("john@gmail.com");
        User savedUser = userService.registerUser(user, profile);
        assertNotNull(savedUser);
        assertEquals(savedUser.getUsername(), user.getUsername());
    }

    @Test
    public void saveUsers_withDuplicateEmail_TestUserService() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        userService.registerUser(user1, profile);
        User user2 = new User();
        user2.setUsername("john");
        user2.setPassword("password");
        user2.setEmail("john@gmail.com");
        assertThrows(DuplicateEmailException.class, () -> userService.registerUser(user2, profile));
        assertEquals(1, userService.count());
    }

    @Test
    public void saveUser_loginUser_testUserService() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        userService.registerUser(user1, profile);
        User loginUser = userService.login("john@gmail.com", "password");
        assertEquals(user1, loginUser);
    }

    @Test
    public void saveUser_loginUserWithWrongPassword_testUserService() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        userService.registerUser(user1, profile);
        assertThrows(InvalidPasswordException.class, () -> userService.login("john@gmail.com", "wrongPassword"));
    }

    @Test
    public void saveUser_loginUserWithWrongEmail_testUserService() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        userService.registerUser(user1, profile);
        assertThrows(UserNotFoundException.class, () -> userService.login("wrong@gmail.com", "password"));
    }

    @Test
    public void saveUser_deleteAccountById() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        userService.registerUser(user1, profile);
        userService.deleteAccountById(user1.getId());
        assertEquals(0, userService.count());
    }
}