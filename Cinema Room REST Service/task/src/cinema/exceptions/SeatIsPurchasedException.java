package cinema.exceptions;


public class SeatIsPurchasedException extends CinemaException {
  public SeatIsPurchasedException() {
    super("The ticket has been already purchased!");
  }
}
