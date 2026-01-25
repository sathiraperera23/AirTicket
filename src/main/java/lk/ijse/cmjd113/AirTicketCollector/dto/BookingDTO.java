package lk.ijse.cmjd113.AirTicketCollector.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDTO {
    private String bookingId;
    private String booking_ref;
    private LocalDateTime booking_date;
    private int seat_count;
    private double total_amt;
    private String status;
    private String user_id;
    private String flight_id;


}
