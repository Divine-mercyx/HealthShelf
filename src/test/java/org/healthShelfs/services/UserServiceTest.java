package org.healthShelfs.services;

import org.healthShelfs.data.models.users.User;
import org.healthShelfs.data.models.users.UserProfile;
import org.healthShelfs.definedExceptions.DuplicateEmailException;
import org.healthShelfs.definedExceptions.InvalidPasswordException;
import org.healthShelfs.definedExceptions.UserNotFoundException;
import org.healthShelfs.services.Dto.UserLoginRequest;
import org.healthShelfs.services.Dto.UserRegistrationRequest;
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
    private UserRegistrationRequest request;
    private UserLoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        profile = new UserProfile();
        userService.deleteAll();
        request = new UserRegistrationRequest();
        loginRequest = new UserLoginRequest();
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
        request.setUser(user);
        request.setProfile(profile);
        User savedUser = userService.registerUser(request);
        assertNotNull(savedUser);
        assertEquals(savedUser.getUsername(), user.getUsername());
    }

    @Test
    public void saveUsers_withDuplicateEmail_TestUserService() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        request.setUser(user1);
        request.setProfile(profile);
        userService.registerUser(request);
        User user2 = new User();
        user2.setUsername("john");
        user2.setPassword("password");
        user2.setEmail("john@gmail.com");
        request.setUser(user2);
        request.setProfile(profile);
        assertThrows(DuplicateEmailException.class, () -> userService.registerUser(request));
        assertEquals(1, userService.count());
    }

    @Test
    public void saveUser_loginUser_testUserService() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        request.setUser(user1);
        request.setProfile(profile);
        userService.registerUser(request);
        loginRequest.setEmail("john@gmail.com");
        loginRequest.setPassword("password");
        User loginUser = userService.login(loginRequest);
        assertEquals(user1, loginUser);
    }

    @Test
    public void saveUser_loginUserWithWrongPassword_testUserService() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        request.setUser(user1);
        request.setProfile(profile);
        userService.registerUser(request);
        loginRequest.setEmail("john@gmail.com");
        loginRequest.setPassword("wrongPassword");
        assertThrows(InvalidPasswordException.class, () -> userService.login(loginRequest));
    }

    @Test
    public void saveUser_loginUserWithWrongEmail_testUserService() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        request.setUser(user1);
        request.setProfile(profile);
        userService.registerUser(request);
        loginRequest.setEmail("wrongJohn@gmail.com");
        loginRequest.setPassword("password");
        assertThrows(UserNotFoundException.class, () -> userService.login(loginRequest));
    }

    @Test
    public void saveUser_deleteAccountById() {
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("password");
        user1.setEmail("john@gmail.com");
        request.setUser(user1);
        request.setProfile(profile);
        userService.registerUser(request);
        userService.deleteAccountById(user1.getId());
        assertEquals(0, userService.count());
    }
}