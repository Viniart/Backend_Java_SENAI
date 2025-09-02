package br.com.techmarket.techmarket_api.handler;

import br.com.techmarket.techmarket_api.exception.BusinessException;
import br.com.techmarket.techmarket_api.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildErrorReponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(BusinessException ex) {
        return buildErrorReponse(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildErrorReponse(Exception ex, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("status", status.getReasonPhrase());
        response.put("error", status.value());
        return new ResponseEntity<>(response, status);
    }
}
