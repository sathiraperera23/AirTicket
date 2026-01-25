package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import lk.ijse.cmjd113.AirTicketCollector.dto.FlightDTO;
import lk.ijse.cmjd113.AirTicketCollector.dto.FlightStatus;
import lk.ijse.cmjd113.AirTicketCollector.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final List<FlightDTO> flights = new ArrayList<>();

    @Override
    public FlightDTO saveFlight(FlightDTO flightDTO) {

        // Validate times
        if (flightDTO.getDepartureTime().isAfter(flightDTO.getArrivalTime())) {
            throw new RuntimeException("Departure time cannot be after arrival time");
        }

        // Prevent duplicate flight numbers
        boolean exists = flights.stream()
                .anyMatch(f -> f.getFlightNo().equals(flightDTO.getFlightNo()));

        if (exists) {
            throw new RuntimeException("Flight number already exists");
        }

        // Default values based on your enum
        flightDTO.setAvailableSeats(flightDTO.getTotalSeats());
        flightDTO.setStatus(FlightStatus.AVAILABLE);

        flights.add(flightDTO);
        return flightDTO;
    }

    @Override
    public FlightDTO getSelectedFlight(String flightNo) {
        return flights.stream()
                .filter(f -> f.getFlightNo().equals(flightNo))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flights;
    }

    @Override
    public void deleteFlight(String flightNo) {
        FlightDTO flight = getSelectedFlight(flightNo);
        flights.remove(flight);
    }

    @Override
    public void updateFlight(String flightNo, FlightDTO flightDTO) {
        FlightDTO existing = getSelectedFlight(flightNo);

        // Optional validation
        if (flightDTO.getDepartureTime() != null &&
                flightDTO.getArrivalTime() != null &&
                flightDTO.getDepartureTime().isAfter(flightDTO.getArrivalTime())) {
            throw new RuntimeException("Invalid flight times");
        }

        existing.setDepartureTime(flightDTO.getDepartureTime());
        existing.setArrivalTime(flightDTO.getArrivalTime());
        existing.setTotalSeats(flightDTO.getTotalSeats());
        existing.setAvailableSeats(flightDTO.getAvailableSeats());
        existing.setBaseFare(flightDTO.getBaseFare());
        existing.setStatus(flightDTO.getStatus());
        existing.setDepartureAirportCode(flightDTO.getDepartureAirportCode());
        existing.setArrivalAirportCode(flightDTO.getArrivalAirportCode());
    }
}
