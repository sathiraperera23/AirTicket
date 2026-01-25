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
        flightDTO.setFlight_id(generateFlightId());

        // default values
        flightDTO.setStatus("SCHEDULED");
        flightDTO.setAvailable_seats(flightDTO.getTotal_seats());

        flights.add(flightDTO);
        return flightDTO;
    }

    @Override
    public FlightDTO getSelectedFlight(String id) {
        return flights.stream()
                .filter(f -> f.getFlight_id().equals(id))
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

        existing.setFlight_no(flightDTO.getFlight_no());
        existing.setDeparture_time(flightDTO.getDeparture_time());
        existing.setArrival_time(flightDTO.getArrival_time());
        existing.setTotal_seats(flightDTO.getTotal_seats());
        existing.setAvailable_seats(flightDTO.getAvailable_seats());
        existing.setBase_fare(flightDTO.getBase_fare());
        existing.setStatus(flightDTO.getStatus());
        existing.setDep_airport_id(flightDTO.getDep_airport_id());
        existing.setArr_airport_id(flightDTO.getArr_airport_id());
    }
}
