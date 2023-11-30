package com.mkdk72ki.asgmt10.service;

import com.mkdk72ki.asgmt10.entity.User;
import com.mkdk72ki.asgmt10.exception.UserExistsException;
import com.mkdk72ki.asgmt10.exception.UserNotFoundException;
import com.mkdk72ki.asgmt10.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // GET
    public List<User> findUsers(String ruby){
        List<User> getUsers;
        if (Objects.isNull(ruby)){
            getUsers = userMapper.findAll();
        } else {
            getUsers = userMapper.findByRuby(ruby);
            if(getUsers.isEmpty()){
                throw new UserNotFoundException("user not found");
            }
        }
        return getUsers;
    }

    public User findById(int id){
        return userMapper.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    // POST
    public User createUser(String name, String ruby, LocalDate birthday, String email){
        User user = new User(null, name, ruby, birthday, email);
        if(userMapper.findUser(user.getEmail()).isPresent()){
            throw new UserExistsException("user already exists");
        } else {
            userMapper.createUser(user);
            return user;
        }
    }

    // PATCH
    public void updateUser(int id, String name, String ruby, LocalDate birthday, String email){
        User user  = this.userMapper.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
            user.updateUser(name, ruby, birthday, email);
        this.userMapper.updateUser(user);
    }

    // DELETE
    public void deleteUser(int id){
        userMapper.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        userMapper.deleteUser(id);
    }

}
