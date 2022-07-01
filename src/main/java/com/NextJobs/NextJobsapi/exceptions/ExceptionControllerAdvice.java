package com.NextJobs.NextJobsapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(value = EmailNotValidException.class)
    public final ResponseEntity<String> handleEmailNotValidException(EmailNotValidException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserExistException.class)
    public final ResponseEntity<String> handleUserExistException(UserExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InternalServerException.class)
    public final ResponseEntity<String> handleInternalServerError(InternalServerException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value =  AuthenticationFailException.class)
    public final ResponseEntity<String> handleCustomException(AuthenticationFailException authenticationFailException){
        return new ResponseEntity<>(authenticationFailException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    public final ResponseEntity<String> invalidTokenException(InvalidTokenException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
