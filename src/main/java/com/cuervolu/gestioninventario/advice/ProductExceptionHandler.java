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

/**
 * Clase que proporciona manejo de excepciones global para el controlador relacionado con productos.
 *
 * <p>Contiene handlers para diferentes tipos de excepciones, proporcionando respuestas HTTP y
 * mensajes personalizados en consecuencia.
 *
 * @author Cuervolu
 * @since 1.0.0
 */
@RestControllerAdvice
public class ProductExceptionHandler {

  /**
   * Maneja excepciones de argumentos de método no válidos.
   *
   * @param ex Excepción de argumentos de método no válidos.
   * @return Mapa de errores que detalla los problemas de validación.
   */
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

  /**
   * Maneja excepciones de recurso no encontrado.
   *
   * @param ex Excepción de recurso no encontrado.
   * @return Respuesta HTTP con un mensaje de error detallado.
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ResponseMessage> handleNotFoundException(NotFoundException ex) {
    ResponseMessage errorResponse =
        ResponseMessage.builder().message(ex.getMessage()).object(null).build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  /**
   * Maneja excepciones de solicitud incorrecta.
   *
   * @param ex Excepción de solicitud incorrecta.
   * @return Respuesta HTTP con un mensaje de error detallado.
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ResponseMessage> handleBadRequestException(BadRequestException ex) {
    ResponseMessage errorResponse =
        ResponseMessage.builder().message(ex.getMessage()).object(null).build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  /**
   * Maneja excepciones de método no permitido.
   *
   * @param ex Excepción de método no permitido.
   * @return Respuesta HTTP con un mensaje de error detallado.
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ResponseMessage> handleMethodNotAllowedException(
      HttpRequestMethodNotSupportedException ex) {
    ResponseMessage errorResponse =
        ResponseMessage.builder().message(ex.getMessage()).object(null).build();
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
  }

  /**
   * Maneja excepciones de mensaje HTTP no legible.
   *
   * @param ex Excepción de mensaje HTTP no legible.
   * @return Respuesta HTTP con un mensaje de error detallado.
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ResponseMessage> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    ResponseMessage errorResponse =
        ResponseMessage.builder().message(ex.getMessage()).object(null).build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
