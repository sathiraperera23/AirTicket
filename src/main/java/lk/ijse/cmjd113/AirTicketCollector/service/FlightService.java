package lk.ijse.cmjd113.AirTicketCollector.service;

import lk.ijse.cmjd113.AirTicketCollector.dto.FlightDTO;

import java.util.List;

public interface FlightService {

    FlightDTO saveFlight(FlightDTO flightDTO);

    FlightDTO getSelectedFlight(String flightNo);

    List<FlightDTO> getAllFlights();

    void deleteFlight(String flightNo);

    void updateFlight(String flightNo, FlightDTO flightDTO);
}
