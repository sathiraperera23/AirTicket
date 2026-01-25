package lk.ijse.cmjd113.AirTicketCollector.service;

import lk.ijse.cmjd113.AirTicketCollector.dto.AirportDTO;

import java.util.List;

public interface AirportService {
    void saveAirport(AirportDTO airportDTO);
    AirportDTO getSelectedAirport(String id);
    List<AirportDTO> getAllAirports();
    void deleteAirport(String id);
    void updateAirport(String id, AirportDTO airportDTO);
}
