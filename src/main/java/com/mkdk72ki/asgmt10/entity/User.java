package com.mkdk72ki.asgmt10.entity;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String ruby;
    private LocalDate birthday;
    private String email;

    public User(Integer id, String name, String ruby, LocalDate birthday, String email) {
        this.id = id;
        this.name = name;
        this.ruby = ruby;
        this.birthday = birthday;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRuby() {
        return ruby;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRuby(String ruby) {
        this.ruby = ruby;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void update(String name, String ruby, LocalDate birthday, String email) {
        if (name != null) {

            this.setName(name);

        }

        if (ruby != null) {

            this.setRuby(ruby);

        }

        if (birthday != null) {

            this.setBirthday(birthday);

        }

        if (email != null) {

            this.setEmail(email);

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(ruby, user.ruby) && Objects.equals(birthday, user.birthday) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ruby, birthday, email);
    }
}
