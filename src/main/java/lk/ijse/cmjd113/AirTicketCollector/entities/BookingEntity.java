package lk.ijse.cmjd113.AirTicketCollector.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="bookings")
public class BookingEntity {
    @Id
    private String bookingId;
    @Column(nullable=false, unique=true)
    private String booking_ref;
    private LocalDateTime booking_date;
    private int seat_count;
    private double total_amt;
    private String status;
    private String user_id;
    private String flight_id;
}
