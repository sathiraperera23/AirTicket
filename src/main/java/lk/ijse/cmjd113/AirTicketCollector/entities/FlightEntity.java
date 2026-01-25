package lk.ijse.cmjd113.AirTicketCollector.entities;

import jakarta.persistence.*;
import lk.ijse.cmjd113.AirTicketCollector.dto.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "flight")
public class FlightEntity {
    @Id
    private String flight_id;
    private String flight_no;
    private LocalDateTime departure_time;
    private LocalDateTime arrival_time;
    private int total_seats;
    private int available_seats;
    private double base_fare;
    private FlightStatus status;
    @JoinColumn(name="dep_airport")
    @ManyToOne(fetch = FetchType.LAZY)
    private AirportEntity departureAirport;
    @JoinColumn(name="arr_airport")
    @ManyToOne(fetch = FetchType.LAZY)
    private AirportEntity arrivalAirport;
}
