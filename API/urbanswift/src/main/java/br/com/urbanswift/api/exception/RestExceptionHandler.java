package br.com.urbanswift.api.exception;

import br.com.urbanswift.api.exception.model.ApiError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiError apiError = new ApiError(
                status.value(),                 // ex: 404
                status.getReasonPhrase(),       // ex: "Not Found"
                ex.getMessage(),                // A mensagem da sua exceção
                request.getRequestURI()         // ex: "/api/usuarios/99"
        );

        // Retorna a ResponseEntity contendo o objeto ApiError e o status HTTP correto
        return new ResponseEntity<>(apiError, status);
    }
}
