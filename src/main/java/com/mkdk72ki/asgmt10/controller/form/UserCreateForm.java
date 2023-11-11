package com.mkdk72ki.asgmt10.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserCreateForm {

    @NotBlank(message = "入力してください")
    @Length(max = 20, message = "20字以内で入力してください")
    private String name;

    @NotBlank(message = "入力してください")
    @Length(max = 50, message = "50字以上で入力してください")
    private String ruby;

    @NotNull(message = "入力してください")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthday;

    @NotBlank(message = "入力してください")
    @Length(max = 100, message = "100字以内で入力してください")
    private String email;

    public UserCreateForm(String name, String ruby, LocalDate birthday, String email) {
        this.name = name;
        this.ruby = ruby;
        this.birthday = birthday;
        this.email = email;
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
}
