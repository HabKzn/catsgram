package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

    private UserService userService;
    private final List<Post> posts = new ArrayList<>();

    @Autowired
    public PostService(final UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(int size, int from, String sort) {
        int to = size + from;
        if (to > posts.size() - 1) {
            to = posts.size() - 1;
        }
        if (from >= posts.size()) {
            return null;
        }
        List<Post> tempList = posts.subList(from, to);
        Collections.sort(tempList, new Comparator<Post>() {
            @Override
            public int compare(final Post o1, final Post o2) {
                if (sort.equals("asc")) {
                    return o1.getCreationDate().compareTo(o2.getCreationDate());
                } else return o1.getCreationDate().compareTo(o2.getCreationDate()) < 0 ? 1 : -1;
            }
        });
        return tempList;
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

    public UserService getUserService() {
        return userService;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Post> findAllByUserEmail(String email, Integer size, String sort) {
        return posts.stream().filter(p -> email.equals(p.getAuthor())).sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate());
            if(sort.equals("desc")){
                comp = -1 * comp;
            }
            return comp;
        }).limit(size).collect(Collectors.toList());
    }
}