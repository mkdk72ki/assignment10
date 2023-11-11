package com.mkdk72ki.asgmt10.controller;

import com.mkdk72ki.asgmt10.controller.form.UserCreateForm;
import com.mkdk72ki.asgmt10.controller.response.MessageResponse;
import com.mkdk72ki.asgmt10.entity.User;
import com.mkdk72ki.asgmt10.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<User> findUsers(@RequestParam(required = false) String ruby){
        List<User> getUsers = userService.findUsers(ruby);
        return getUsers;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findById(@PathVariable int id){
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<MessageResponse> createUser(@RequestBody @Validated UserCreateForm userCreateForm, UriComponentsBuilder uriComponentsBuilder){
        User user = userService.createUser(userCreateForm.getName(), userCreateForm.getRuby(), userCreateForm.getBirthday(), userCreateForm.getEmail());
        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        MessageResponse body = new MessageResponse("登録しました");
        return ResponseEntity.created(uri).body(body);
    }
}
