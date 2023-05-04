package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User saveUser = userService.saveUser(user);
        return ResponseEntity.ok(saveUser);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") long id) {
        user.setUser_id(id);
        User updateuser = userService.saveUser(user);
        return ResponseEntity.ok(updateuser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
