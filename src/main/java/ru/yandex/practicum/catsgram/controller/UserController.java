package ru.yandex.practicum.catsgram.controller;


import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Collection;

@RestController()
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    Collection<User> users() {
        return userService.findAll();
    }

    @PostMapping
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    User refreshUser(@RequestBody User user) {
        return userService.refreshUser(user);
    }

    @GetMapping("/user/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

}
