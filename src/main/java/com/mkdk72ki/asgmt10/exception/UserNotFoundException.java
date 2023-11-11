package com.mkdk72ki.asgmt10.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    // 指定されたメッセージと原因を持つ例外を作成する
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // 指定されたメッセージを持つ例外を作成する
    public UserNotFoundException(String message) {
        super(message);
    }

    // 指定された原因を持つ例外を作成する
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
