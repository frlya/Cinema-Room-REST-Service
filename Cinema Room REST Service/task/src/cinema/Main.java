package cinema;

import cinema.exceptions.NoSeatFoundException;
import cinema.exceptions.SeatIsPurchasedException;
import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongTokenException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

class Refund {
  private String token;

  public Refund() {}

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

@RestController
class CinemaController {

  private final Cinema ourGreatCinema = new Cinema(9,9);
  private final Map<UUID, Seat> reservationMap = new HashMap<>();

  @GetMapping("/seats")
  Cinema getSeats() { return ourGreatCinema; }

  @PostMapping("/purchase")
  Ticket purchaseSeat(@RequestBody SeatRequest request) {
    Optional<Seat> targeted = ourGreatCinema.getAvailable_seats().stream()
        .filter( seat -> seat.isSameSeatAs(request))
        .findFirst();
    if(targeted.isEmpty())
      throw new NoSeatFoundException();

    Seat target = targeted.get();
    if(reservationMap.containsValue(target))
      throw new SeatIsPurchasedException();

    UUID token = UUID.randomUUID();
    reservationMap.put(token, target);
    return new Ticket(token, target);
  }


  @PostMapping("/return")
  Map<String, Seat> refundTicket(@RequestBody Refund refund) {
    UUID token = UUID.fromString(refund.getToken());
    if(!reservationMap.containsKey(token))
      throw new WrongTokenException();
    Seat refunded = reservationMap.get(token);
    reservationMap.remove(token);
    return Map.of("returned_ticket", refunded);
  }

  @PostMapping("/stats")
  Stats getStats(
      @RequestParam(required = false) String password
  ) {
    if(!Objects.equals(password, "super_secret"))
      throw new WrongPasswordException();
    Collection<Seat> reservations = reservationMap.values();
    return new Stats(
       reservations.stream().map(Seat::getPrice).reduce(0, Integer::sum),
        ourGreatCinema.getAvailable_seats().size() - reservations.size(),
        reservations.size()
    );
  }

}