package cinema;


import cinema.Seat;
import java.util.UUID;

public class Ticket {
  private final UUID token;
  private final Seat ticket;

  public Ticket(UUID token, Seat ticket) {
    this.token = token;
    this.ticket = ticket;
  }

  public UUID getToken() {
    return token;
  }

  public Seat getTicket() {
    return ticket;
  }
}
