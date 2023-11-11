package com.mkdk72ki.asgmt10.controller;

import com.mkdk72ki.asgmt10.entity.User;
import com.mkdk72ki.asgmt10.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
