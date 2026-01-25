package lk.ijse.cmjd113.AirTicketCollector.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassengerDTO {
    private String passenger_id;
    private String first_name;
    private String last_name;
    private int age;
    private String gender;
    private String contact_number;
    private String seat_number;
    private String booking_id;
}
