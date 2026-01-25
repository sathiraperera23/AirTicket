package lk.ijse.cmjd113.AirTicketCollector.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="passengers")
public class PassengerEntity {
    @Id
    private String passenger_id;
    private String first_name;
    private String last_name;
    private int age;
    private String gender;
    private String contact_number;
    private String seat_number;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private BookingEntity booking_id;
}
