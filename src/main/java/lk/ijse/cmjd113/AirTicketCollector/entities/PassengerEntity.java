package lk.ijse.cmjd113.AirTicketCollector.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "passengers")
public class PassengerEntity implements Serializable {

    @Id
    private String passengerId;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String contactNumber;
    private String seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private BookingEntity booking; // ⚠ renamed from booking_id -> booking
}
