package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import lk.ijse.cmjd113.AirTicketCollector.dto.FlightDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final List<FlightDTO> flights = new ArrayList<>();
    private int flightCounter = 0;

    private String generateFlightId() {
        flightCounter++;
        return String.format("FLT-%03d", flightCounter);
    }

    @Override
    public FlightDTO saveFlight(FlightDTO flightDTO) {
        flightDTO.setAirport_id(generateFlightId());
        flights.add(flightDTO);
        return flightDTO;
    }

    @Override
    public FlightDTO getSelectedFlight(String id) {
        return flights.stream()
                .filter(f -> f.getAirport_id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flights;
    }

    @Override
    public void deleteFlight(String id) {
        FlightDTO flight = getSelectedFlight(id);
        flights.remove(flight);
    }

    @Override
    public void updateFlight(String id, FlightDTO flightDTO) {
        FlightDTO existing = getSelectedFlight(id);

        existing.setAirport_code(flightDTO.getAirport_code());
        existing.setAirport_name(flightDTO.getAirport_name());
        existing.setCity(flightDTO.getCity());
    }
}
