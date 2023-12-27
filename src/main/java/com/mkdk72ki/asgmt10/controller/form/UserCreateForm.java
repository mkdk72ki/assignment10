package com.mkdk72ki.asgmt10.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserCreateForm {

  @NotBlank(groups = ValidGroup1.class, message = "入力してください")
  @Length(groups = ValidGroup2.class, max = 20, message = "20字以内で入力してください")
  private String name;

  @NotBlank(groups = ValidGroup1.class, message = "入力してください")
  @Length(groups = ValidGroup2.class, max = 50, message = "50字以内で入力してください")
  private String ruby;

  @NotNull(groups = ValidGroup1.class, message = "入力してください")
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate birthday;

  @NotBlank(groups = ValidGroup1.class, message = "入力してください")
  @Length(groups = ValidGroup2.class, max = 100, message = "100字以内で入力してください")
  @Pattern(regexp = "^([a-zA-Z0-9])+([a-zA-Z0-9._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9._-])+$", groups = ValidGroup2.class, message = "形式が正しくありません")
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
