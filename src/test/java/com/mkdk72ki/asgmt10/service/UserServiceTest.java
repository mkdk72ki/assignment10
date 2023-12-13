package com.mkdk72ki.asgmt10.service;

import com.mkdk72ki.asgmt10.entity.User;
import com.mkdk72ki.asgmt10.exception.UserExistsException;
import com.mkdk72ki.asgmt10.exception.UserNotFoundException;
import com.mkdk72ki.asgmt10.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserMapper userMapper;

    // GET

    @Test
    public void 正常に全てのユーザーが返されること() throws Exception {
        List<User> user = List.of(
                new User(1, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com"),
                new User(2, "加藤花子", "kato hanako", LocalDate.of(2000, 11, 23), "kato@mkdk.com"),
                new User(3, "鈴木祐介", "suzuki yusuke", LocalDate.of(2005, 07, 16), "suzuki@mkdk.com")
        );
        doReturn(user).when(userMapper).findAll();
        List<User> actual = userService.findUsers(null);
        assertThat(actual).isEqualTo(user);
        verify(userMapper, times(1)).findAll();
    }

    @Test
    public void ルビを指定したときに正常にルビと前方一致したユーザーが返されること() throws Exception {
        List<User> user = List.of(
                new User(1, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com"),
                new User(2, "山田花子", "yamada hanako", LocalDate.of(2000, 11, 23), "hanako@mkdk.com"),
                new User(3, "山田祐介", "yamada yusuke", LocalDate.of(2005, 07, 16), "yusuke@mkdk.com")
        );
        doReturn(user).when(userMapper).findByRuby("yamada");
        List<User> actual = userService.findUsers("yamada");
        assertThat(actual).isEqualTo(user);
        verify(userMapper, times(1)).findByRuby("yamada");
    }

    @Test
    public void ユーザーの存在しないルビを指定したときに空のリストが返されること() throws Exception {
        List<User> user = List.of();
        doReturn(user).when(userMapper).findByRuby("john");
        List<User> actual = userService.findUsers("john");
        assertThat(actual).isEqualTo(user);
        verify(userMapper, times(1)).findByRuby("john");
    }

    @Test
    public void 存在するユーザーのIDを指定したときに正常にユーザーが返されること() throws Exception {
        User user = new User(1, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com");
        doReturn(Optional.of(user)).when(userMapper).findById(1);
        User actual = userService.findById(1);
        assertThat(actual).isEqualTo(user);
        verify(userMapper, times(1)).findById(1);
    }

    @Test
    public void 存在しないユーザーのIDを指定したときに適切な例外が返されること() throws Exception {
        doReturn(Optional.empty()).when(userMapper).findById(99);
        assertThrows(UserNotFoundException.class, () -> {
            userService.findById(99);
        });
    }

    // POST

    @Test
    public void 正常に新規のユーザーが登録できること() throws Exception {
        User user = new User(null, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com");
        doNothing().when(userMapper).createUser(user);

        User actual = userService.createUser("山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com");
        assertThat(actual).isEqualTo(user);
        verify(userMapper, times(1)).createUser(user);
    }

    @Test
    public void 既に存在するメールアドレスを指定したときに適切な例外が返されること() throws Exception {
        User user = new User(null, "山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com");
        String email = "yamada@mkdk.com";
        doReturn(Optional.of(user)).when(userMapper).findUser(email);
        assertThrows(UserExistsException.class, () -> {
            userService.createUser("山田太郎", "yamada taro", LocalDate.of(1990, 03, 04), "yamada@mkdk.com");
        });
    }

}
