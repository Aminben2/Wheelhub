package com.WheelHub.WheelHub.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
class ErrorHandlingControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> onConstraintValidationException(ConstraintViolationException e) {
    ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();
    Map<String, String> error = new HashMap<>();
    error.put("error", violation.getMessage());
    return error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    FieldError fieldError = e.getBindingResult().getFieldError();
    Map<String, String> error = new HashMap<>();
    if (fieldError != null) {
      error.put("error", fieldError.getDefaultMessage());
    }
    return error;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleEntityNotFoundException(
      EntityNotFoundException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<Map<String, String>> handleDuplicateResourceException(
      DuplicateResourceException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, String>> handleIllegalArgumentException(
      IllegalArgumentException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Access denied: " + ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Map<String, String>> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "HTTP method not supported: " + ex.getMethod());
    return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Malformed request body: " + ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Database constraint violation: " + ex.getMostSpecificCause().getMessage());
    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<Map<String, String>> handleNoHandlerFoundException(
      NoHandlerFoundException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "No handler found for the request: " + ex.getRequestURL());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Map<String, String>> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Missing request parameter: " + ex.getParameterName());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<Map<String, String>> handleMaxUploadSizeExceededException(
      MaxUploadSizeExceededException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "File size exceeds the limit");
    return new ResponseEntity<>(error, HttpStatus.PAYLOAD_TOO_LARGE);
  }

  @ExceptionHandler(MissingPathVariableException.class)
  public ResponseEntity<Map<String, String>> handleMissingPathVariableException(
      MissingPathVariableException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Missing path variable: " + ex.getVariableName());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AsyncRequestTimeoutException.class)
  public ResponseEntity<Map<String, String>> handleAsyncRequestTimeoutException(
      AsyncRequestTimeoutException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Request timeout");
    return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  public ResponseEntity<Map<String, String>> handleHttpMediaTypeNotAcceptableException(
      HttpMediaTypeNotAcceptableException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Media type not acceptable");
    return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<Map<String, String>> handleHttpMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Unsupported media type: " + ex.getContentType());
    return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<Map<String, String>> handleBindException(BindException ex) {
    Map<String, String> error = new HashMap<>();
    FieldError fieldError = ex.getBindingResult().getFieldError();
    if (fieldError != null) {
      error.put("error", fieldError.getDefaultMessage());
    }
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
