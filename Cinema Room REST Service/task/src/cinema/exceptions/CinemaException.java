package cinema.exceptions;

public class CinemaException extends RuntimeException {
  private final String error;

  public CinemaException(String error) {
    this.error = error;
  }

  public String getError() { return error; }
}
