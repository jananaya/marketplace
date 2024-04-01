package edu.unimagdalena.marketplace.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .distinct()
                .toList();
        ErrorResponse response = new ErrorResponse(
                ex.getClass().getSimpleName(),
                req.getRequestURI(),
                errorMessages);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = {
            BadRequestException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(HttpServletRequest req, BadRequestException ex) {
        ErrorResponse errorResponse = getErrorResponse(req, ex);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest req, NotFoundException ex) {
        ErrorResponse errorResponse = getErrorResponse(req, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private static ErrorResponse getErrorResponse(HttpServletRequest req, RuntimeException ex) {
        List<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getClass().getSimpleName(),
                req.getRequestURI(),
                messages);
        return errorResponse;
    }
}
