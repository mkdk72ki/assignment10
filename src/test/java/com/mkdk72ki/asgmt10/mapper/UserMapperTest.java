package com.mkdk72ki.asgmt10.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.mkdk72ki.asgmt10.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 全てのユーザーが取得できること() {
        List<User> users = userMapper.findAll();
        assertThat(users)
                .hasSize(3)
                .contains(
                        new User(1, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com"),
                        new User(2, "加藤花子", "kato hanako", LocalDate.of(2000, 11, 23), "kato@mkdk.com"),
                        new User(3, "鈴木祐介", "suzuki yusuke", LocalDate.of(2005, 07, 16), "suzuki@mkdk.com")
                );
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void ルビで指定したユーザーが取得できること() {
        List<User> users = userMapper.findByRuby("yamada");
        assertThat(users)
                .hasSize(1)
                .contains(
                        new User(1, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com")
                );
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 存在しないルビを指定したときに取得されるユーザーが空であること() {
        List<User> users = userMapper.findByRuby("John");
        assertThat(users).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void IDで指定したユーザーが取得できること() {
        Optional<User> users = userMapper.findById(1);
        assertThat(users)
                .contains(
                        new User(1, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com")
                );
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 存在しないIDを指定したときに取得されるユーザーが空であること() {
        Optional<User> users = userMapper.findById(99);
        assertThat(users).isEmpty();
    }

}
