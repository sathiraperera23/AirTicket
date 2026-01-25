package lk.ijse.cmjd113.AirTicketCollector.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightDTO {
   private String flight_id;
   private String flight_no;
   private LocalDateTime departure_time;
   private LocalDateTime arrival_time;
   private int total_seats;
   private int available_seats;
   private double base_fare;
   private String status;
   private String dep_airport_id;
   private String arr_airport_id;

}
