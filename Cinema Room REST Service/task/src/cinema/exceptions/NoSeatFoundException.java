package cinema.exceptions;


public class NoSeatFoundException extends CinemaException{
  public NoSeatFoundException() {
    super("The number of a row or a column is out of bounds!");
  }
}
