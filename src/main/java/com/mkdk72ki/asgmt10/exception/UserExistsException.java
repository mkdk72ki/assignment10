package com.mkdk72ki.asgmt10.exception;

public class UserExistsException extends RuntimeException {
  public UserExistsException() {
    super();
  }

  public UserExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserExistsException(String message) {
    super(message);
  }

  public UserExistsException(Throwable cause) {
    super(cause);
  }
}
