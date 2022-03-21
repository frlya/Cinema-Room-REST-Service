package cinema;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
  private final int total_rows;
  private final int total_columns;
  private final List<Seat> available_seats = new ArrayList<>();

  public Cinema(int total_rows, int total_columns) {
    this.total_rows = total_rows;
    this.total_columns = total_columns;
    for(int i = 1; i < total_rows + 1; ++i) {
      for(int j = 1; j < total_columns + 1; ++j) {
        available_seats.add(new Seat(i, j));
      }
    }
  }

  public int getTotal_rows() { return total_rows; }
  public int getTotal_columns() { return total_columns; }
  public List<Seat> getAvailable_seats() { return available_seats; }
}
