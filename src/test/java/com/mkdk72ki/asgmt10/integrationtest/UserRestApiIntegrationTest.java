package com.mkdk72ki.asgmt10.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    // GET

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void ユーザーが全件取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                    [
                    {
                        "id": 1,
                        "name": "山田太郎",
                        "ruby": "yamada taro",
                        "birthday": "1990-03-04",
                        "email": "yamada@mkdk.com"
                    },
                    {
                        "id": 2,
                        "name": "山田花子",
                        "ruby": "yamada hanako",
                        "birthday": "2000-11-23",
                        "email": "hanako@mkdk.com"
                    },
                    {
                        "id": 3,
                        "name": "小山田祐介",
                        "ruby": "oyamada yusuke",
                        "birthday": "2005-07-16",
                        "email": "oyamada@mkdk.com"
                    },
                    {
                        "id": 4,
                        "name": "山田次郎",
                        "ruby": "YAMADA JIRO",
                        "birthday": "1995-12-30",
                        "email": "jiro@mkdk.com"
                    }
                ]
                                """, response, JSONCompareMode.STRICT);

    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void ルビで指定したユーザーが取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users?ruby=yamada"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                    [
                    {
                        "id": 1,
                        "name": "山田太郎",
                        "ruby": "yamada taro",
                        "birthday": "1990-03-04",
                        "email": "yamada@mkdk.com"
                    },
                    {
                        "id": 2,
                        "name": "山田花子",
                        "ruby": "yamada hanako",
                        "birthday": "2000-11-23",
                        "email": "hanako@mkdk.com"
                    }
                ]
                                """, response, JSONCompareMode.STRICT);

    }


    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void ルビを大文字で指定したときにユーザーが取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users?ruby=YAMADA"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                    [
                      {
                        "id": 1,
                        "name": "山田太郎",
                        "ruby": "yamada taro",
                        "birthday": "1990-03-04",
                        "email": "yamada@mkdk.com"
                    },
                    {
                        "id": 2,
                        "name": "山田花子",
                        "ruby": "yamada hanako",
                        "birthday": "2000-11-23",
                        "email": "hanako@mkdk.com"
                    }
                ]
                                """, response, JSONCompareMode.STRICT);

    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 存在しないルビを指定したときに空のリストが返ること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users?ruby=john"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                []
                            """, response, JSONCompareMode.STRICT);

    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void IDで指定したユーザーが取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                    "id": 1,
                    "name": "山田太郎",
                    "ruby": "yamada taro",
                    "birthday": "1990-03-04",
                    "email": "yamada@mkdk.com"
                }
                            """, response, JSONCompareMode.STRICT);

    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 存在しないIDを指定したときに404エラーが返されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 99))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                    "error": "Not Found",
                    "timestamp": "2023-12-19T13:22:34.194271200+09:00[Asia/Tokyo]",
                    "path": "/users/99",
                    "status": "404"
                }
                """, response, new CustomComparator(JSONCompareMode.STRICT, new Customization("timestamp", ((o1, o2) -> true))));
    }

    // POST

    @Test
    @DataSet(value = "datasets/users.yml")
    @ExpectedDataSet(value = "datasets/insert-users.yml", ignoreCols = "id")
    @Transactional
    void ユーザーが登録できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                                {
                                 "name": "田村敦",
                                 "ruby": "tamura atsushi",
                                 "birthday": "1995-09-18",
                                 "email": "tamura@mkdk.com"
                                }
                                 """)).andExpect(MockMvcResultMatchers.status().isCreated()).
                andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                    "message": "登録しました"
                }
                            """, response, JSONCompareMode.STRICT);

    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 名前が空文字で登録したときに400エラーが返されること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                                {
                                  "name": "",
                                  "ruby": "tamura atsushi",
                                  "birthday": "1995-09-18",
                                  "email": "tamura@mkdk.com"
                                 }
                                  """)).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void ルビが空文字で登録したときに400エラーが返されること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                                {
                                  "name": "田村敦",
                                  "ruby": "",
                                  "birthday": "1995-09-18",
                                  "email": "tamura@mkdk.com"
                                 }
                                  """)).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 名前が20字以上ルビが50字以上で登録したときに400エラーが返されること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                                {
                                  "name": "aaaaaaaaaaaaaaaaaaaaa",
                                  "ruby": "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                                  "birthday": "1995-09-18",
                                  "email": "tamura@mkdk.com"
                                 }
                                  """)).andExpect(MockMvcResultMatchers.status().isBadRequest()).
                andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 誕生日が空文字で登録したときに400エラーが返されること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                                {
                                  "name": "田村敦",
                                  "ruby": "tamura atsushi",
                                  "birthday": "",
                                  "email": "tamura@mkdk.com"
                                 }
                                  """)).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void メールアドレスが空文字で登録したときに400エラーが返されること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                                {
                                  "name": "田村敦",
                                  "ruby": "tamura atsushi",
                                  "birthday": "1995-09-18",
                                  "email": ""
                                 }
                                  """)).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void メールアドレスが100字以上で登録したときに400エラーが返されること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                                {
                                  "name": "田村敦",
                                  "ruby": "tamura atsushi",
                                  "birthday": "1995-09-18",
                                  "email": "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@mkdk.com"
                                 }
                                  """)).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 不適切な形式のメールアドレスで登録したときに400エラーが返されること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                                {
                                  "name": "田村敦",
                                  "ruby": "tamura atsushi",
                                  "birthday": "1995-09-18",
                                  "email": "tamura"
                                 }
                                  """)).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 存在するメールアドレスのユーザーを登録したときに409エラーが返されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                                {
                                "name": "山田太郎",
                                "ruby": "yamada taro",
                                "birthday": "1990-03-04",
                                "email": "yamada@mkdk.com"
                                }
                                             """)).andExpect(MockMvcResultMatchers.status().isConflict())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                            {
                                  "path": "/users",
                                  "status": "409",
                                  "message": "user already exists",
                                  "timestamp": "2023-12-19T13:44:06.214687600+09:00[Asia/Tokyo]",
                                  "error": "Conflict"
                              }
                """, response, new CustomComparator(JSONCompareMode.STRICT, new Customization("timestamp", ((o1, o2) -> true))));
    }
}
