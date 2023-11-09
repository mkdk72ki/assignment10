package com.mkdk72ki.asgmt10.mapper;

import com.mkdk72ki.asgmt10.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    // GET
    @Select("SELECT * FROM users")
    List<User> findAll();

    @Select("SELECT * FROM users WHERE ruby LIKE CONCAT(#{ruby}, '%')")
    List<User> findByRuby(String ruby);

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<User> findById(int id);
}
