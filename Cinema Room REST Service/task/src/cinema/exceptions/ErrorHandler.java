package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(CinemaException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public CinemaException handleCustomException(CinemaException e) {
    return e;
  }

  @ExceptionHandler(WrongPasswordException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public WrongPasswordException handleWrongPassword(WrongPasswordException e) {
    return e;
  }
}