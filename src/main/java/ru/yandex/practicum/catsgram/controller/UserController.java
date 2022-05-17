package ru.yandex.practicum.catsgram.controller;


import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/users")
public class UserController {
    Map<String, User> usersMap = new HashMap<>();

    @GetMapping
    Collection<User> users() {
        return usersMap.values();
    }

    @PostMapping
    User createUser(@RequestBody User user) {
        if (usersMap.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует");
        } else if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Отсутствует адрес электронной почты");
        } else {
            usersMap.put(user.getEmail(), user);
        }
        return usersMap.get(user.getEmail());
    }

    @PutMapping
    User refreshUser(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Отсутствует адрес электронной почты");
        } else if (usersMap.containsKey(user.getEmail())) {
            usersMap.put(user.getEmail(), user);
        }
        return user;
    }
}
