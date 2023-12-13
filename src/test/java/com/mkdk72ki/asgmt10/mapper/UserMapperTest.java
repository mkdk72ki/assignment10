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
                        new User(2, "山田花子", "yamada hanako", LocalDate.of(2000, 11, 23), "hanako@mkdk.com"),
                        new User(3, "小山田祐介", "oyamada yusuke", LocalDate.of(2005, 07, 16), "oyamada@mkdk.com")
                );
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void ルビで指定したユーザーが取得できること() {
        List<User> users = userMapper.findByRuby("yamada");
        assertThat(users)
                .hasSize(2)
                .contains(
                        new User(1, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com"),
                        new User(2, "山田花子", "yamada hanako", LocalDate.of(2000, 11, 23), "hanako@mkdk.com")
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

    // POST

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 存在するメールアドレスを取得したときにそのユーザーのレコードが取得できること() {
        User user = new User(null, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com");
        Optional<User> users = userMapper.findUser(user.getEmail());
        assertThat(users).contains(
                new User(1, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com")
        );
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 存在しないメールアドレスを指定したときに取得されるユーザーが空であること() {
        User user = new User(null, "ジョン", "john", LocalDate.of(1996, 01, 23), "john@mkdk.com");
        Optional<User> users = userMapper.findUser(user.getEmail());
        assertThat(users).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 新たにレコードが登録できること() {
        User user = new User(null, "加藤花子", "kato hanako", LocalDate.of(1999, 02, 22), "kato@mkdk.com");
        userMapper.createUser(user);
        List<User> users = userMapper.findAll();
        assertThat(users).hasSize(5)
                .contains(
                        new User(1, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com"),
                        new User(2, "山田花子", "yamada hanako", LocalDate.of(2000, 11, 23), "hanako@mkdk.com"),
                        new User(3, "小山田祐介", "oyamada yusuke", LocalDate.of(2005, 07, 16), "oyamada@mkdk.com"),
                        new User(4, "山田次郎", "YAMADA JIRO", LocalDate.of(1995, 12, 30), "jiro@mkdk.com"),
                        user
                );
    }

}
