package com.cuervolu.gestioninventario.advice;

import com.cuervolu.gestioninventario.entities.payload.ResponseMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
              errors.put(error.getField(), error.getDefaultMessage());
            });
    return errors;
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ResponseMessage> handleNotFoundException(NotFoundException ex) {
    ResponseMessage errorResponse =
        ResponseMessage.builder().message(ex.getMessage()).object(null).build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ResponseMessage> handleBadRequestException(BadRequestException ex) {
    ResponseMessage errorResponse =
        ResponseMessage.builder().message(ex.getMessage()).object(null).build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ResponseMessage> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex) {
    ResponseMessage errorResponse =
        ResponseMessage.builder().message(ex.getMessage()).object(null).build();
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
  }
  
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ResponseMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    ResponseMessage errorResponse =
        ResponseMessage.builder().message(ex.getMessage()).object(null).build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
