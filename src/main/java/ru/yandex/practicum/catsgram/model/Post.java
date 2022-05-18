package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

public class Post {
    private Integer id;
    private final String author;
    private final Instant creationDate = Instant.now();
    private String description;
    private String photoUrl;

    public Post(String author, String description, String photoUrl, Integer id) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}