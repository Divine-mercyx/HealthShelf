package org.healthShelfs.controllers;

import org.healthShelfs.data.models.User;
import org.healthShelfs.data.models.UserProfile;
import org.healthShelfs.services.Dto.UserRegistrationRequest;
import org.healthShelfs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    public User registerUser(@RequestBody UserRegistrationRequest request) {
        return userService.registerUser(request.getUser(), request.getProfile());
    }

    @GetMapping("/get/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/getUsers")
    public List<User> findAllUsers() {
        return userService.findAll();
    }
}
