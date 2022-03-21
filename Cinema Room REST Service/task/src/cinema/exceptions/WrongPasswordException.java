package cinema.exceptions;

public class WrongPasswordException extends CinemaException {
  public WrongPasswordException() {
    super("The password is wrong!");
  }
}
