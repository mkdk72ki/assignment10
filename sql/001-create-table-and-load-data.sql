DROP TABLE IF EXISTS users;

CREATE TABLE users (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 ruby VARCHAR(50) NOT NULL,
 birthday DATE NOT NULL,
 email VARCHAR(100) NOT NULL UNIQUE,
 PRIMARY KEY(id)
 );

 INSERT INTO users (id, name, ruby, birthday, email) VALUES (1, "山田太郎", "yamada taro", "1990-03-04", "yamada@mkdk.com");
 INSERT INTO users (id, name, ruby, birthday, email) VALUES (2, "加藤花子", "kato hanako", "2000-11-23", "kato@mkdk.com");
 INSERT INTO users (id, name, ruby, birthday, email) VALUES (3, "鈴木祐介", "suzuki yusuke", "2005-07-16", "suzuki@mkdk.com");
 INSERT INTO users (id, name, ruby, birthday, email) VALUES (4, "河野久美", "kawano kumi", "1994-08-30", "kawano@mkdk.com");
