package com.mkdk72ki.asgmt10.service;

import com.mkdk72ki.asgmt10.entity.User;
import com.mkdk72ki.asgmt10.exception.UserNotFoundException;
import com.mkdk72ki.asgmt10.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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
}
