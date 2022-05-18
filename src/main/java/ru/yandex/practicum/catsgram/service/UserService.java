package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {
    Map<String, User> usersMap = new HashMap<>();


    public Collection<User> findAll() {
        return usersMap.values();
    }

    public User createUser(User user){
        if (usersMap.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует");
        } else if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Отсутствует адрес электронной почты");
        } else {
            usersMap.put(user.getEmail(), user);
        }
        return usersMap.get(user.getEmail());
    }

    public User refreshUser (User user ){
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Отсутствует адрес электронной почты");
        } else if (usersMap.containsKey(user.getEmail())) {
            usersMap.put(user.getEmail(), user);
        }
        return user;
    }

   public User findUserByEmail(String email){
        return usersMap.getOrDefault(email, null);
    }
}
