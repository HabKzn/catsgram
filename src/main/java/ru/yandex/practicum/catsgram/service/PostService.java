package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    UserService userService;
    private final List<Post> posts = new ArrayList<>();

    @Autowired
    public PostService(final UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) == null) {
            posts.add(post);
            return post;
        } else throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден");
    }

   public Post findById(Integer postId) {
        return posts.stream()
                .filter(x -> Objects.equals(x.getId(), postId))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException(String.format("Пост № %d не найден", postId)));
    }
}