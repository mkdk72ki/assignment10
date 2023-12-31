package com.mkdk72ki.asgmt10.controller;

import com.mkdk72ki.asgmt10.exception.UserExistsException;
import com.mkdk72ki.asgmt10.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler {

  @ExceptionHandler(value = UserNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNoUserFound(
      HttpServletRequest request) {
    Map<String, String> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
        "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
        "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
        "path", request.getRequestURI());

    // 404エラーを返す
    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

  }

  @ExceptionHandler(value = UserExistsException.class)
  public ResponseEntity<Map<String, String>> handlePersonExists(
      UserExistsException e, HttpServletRequest request) {
    Map<String, String> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
        "status", String.valueOf(HttpStatus.CONFLICT.value()),
        "error", HttpStatus.CONFLICT.getReasonPhrase(),
        "message", e.getMessage(),
        "path", request.getRequestURI());

    // 409エラーを返す
    return new ResponseEntity<>(body, HttpStatus.CONFLICT);

  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationException(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError e : ex.getFieldErrors()) {
      errors.put(e.getField(), e.getDefaultMessage());
    }
    Map<String, Object> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
        "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
        "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
        "message", errors,
        "path", request.getRequestURI());

    // 400エラーを返す
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

  }

}
