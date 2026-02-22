package lk.ijse.cmjd113.AirTicketCollector.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    private String passengerId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String contactNumber;
    private String seatNumber;
    private String bookingId; // reference BookingEntity by ID only
}
