package com.mkdk72ki.asgmt10.mapper;

import com.mkdk72ki.asgmt10.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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

    // POST
    @Select("SELECT id FROM users WHERE email = #{email}")
    Optional<String> findUser(String email);

    @Insert("INSERT INTO users (name, ruby, birthday, email) VALUES (#{name}, #{ruby}, #{birthday}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createUser(User user);
}
