package lk.ijse.cmjd113.AirTicketCollector.service;

import lk.ijse.cmjd113.AirTicketCollector.dto.FlightDTO;

import java.util.List;

public interface FlightService {

    FlightDTO saveFlight(FlightDTO flightDTO);

    FlightDTO getSelectedFlight(String id);

    List<FlightDTO> getAllFlights();

    void deleteFlight(String id);

    void updateFlight(String id, FlightDTO flightDTO);
}
