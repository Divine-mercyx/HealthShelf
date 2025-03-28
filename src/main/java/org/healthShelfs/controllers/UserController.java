package org.healthShelfs.controllers;

import org.healthShelfs.data.models.User;
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
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/get")
    public List<User> findUserById() {
        return userService.findAll();
    }
}
