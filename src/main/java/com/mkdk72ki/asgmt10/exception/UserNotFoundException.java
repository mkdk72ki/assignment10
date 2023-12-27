package com.mkdk72ki.asgmt10.exception;

public class UserNotFoundException extends RuntimeException {

  // 指定されたメッセージを持つ例外を作成する
  public UserNotFoundException(String message) {
    super(message);
  }
}
