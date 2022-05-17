package ru.yandex.practicum.catsgram.model;

import java.time.LocalDate;

public class User {
    private String email;
    private String nickname;
    private LocalDate birthdate;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(final LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}