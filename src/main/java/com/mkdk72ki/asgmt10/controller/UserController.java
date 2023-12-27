package com.mkdk72ki.asgmt10.controller;

import com.mkdk72ki.asgmt10.controller.form.GroupOrder;
import com.mkdk72ki.asgmt10.controller.form.UserCreateForm;
import com.mkdk72ki.asgmt10.controller.form.UserUpdateForm;
import com.mkdk72ki.asgmt10.controller.response.MessageResponse;
import com.mkdk72ki.asgmt10.entity.User;
import com.mkdk72ki.asgmt10.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> findUsers(@RequestParam(required = false) String rubyStartingWith) {
    List<User> getUsers = userService.findUsers(rubyStartingWith);
    return getUsers;
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> findById(@PathVariable int id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/users")
  public ResponseEntity<MessageResponse> createUser(@RequestBody @Validated(GroupOrder.class) UserCreateForm userCreateForm, UriComponentsBuilder uriComponentsBuilder) {
    User user = userService.createUser(userCreateForm.getName(), userCreateForm.getRuby(), userCreateForm.getBirthday(), userCreateForm.getEmail());
    URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
    MessageResponse body = new MessageResponse("登録しました");
    return ResponseEntity.created(uri).body(body);
  }

  @PatchMapping("/users/{id}")
  public ResponseEntity<MessageResponse> updateUser(@PathVariable int id, @RequestBody @Validated UserUpdateForm userUpdateForm) {
    userService.updateUser(id, userUpdateForm.getName(), userUpdateForm.getRuby(), userUpdateForm.getBirthday(), userUpdateForm.getEmail());
    MessageResponse message = new MessageResponse("更新しました");
    return ResponseEntity.ok(message);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<MessageResponse> deleteUser(@PathVariable int id) {
    userService.deleteUser(id);
    MessageResponse message = new MessageResponse("削除しました");
    return ResponseEntity.ok(message);
  }
}
