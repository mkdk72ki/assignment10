package com.mkdk72ki.asgmt10.controller.form;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserUpdateForm {

  @Length(max = 20, message = "20字以内で入力してください")
  private String name;

  @Length(max = 50, message = "50字以上で入力してください")
  private String ruby;

  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate birthday;

  @Length(max = 100, message = "100字以内で入力してください")
  @Pattern(regexp = "^([a-zA-Z0-9])+([a-zA-Z0-9._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9._-])+$")
  private String email;

  public UserUpdateForm(String name, String ruby, LocalDate birthday, String email) {
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
