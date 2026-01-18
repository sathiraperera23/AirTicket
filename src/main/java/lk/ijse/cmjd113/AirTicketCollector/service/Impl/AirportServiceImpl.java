package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import lk.ijse.cmjd113.AirTicketCollector.dto.AirportDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.AirportService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {
    @Override
    public AirportDTO saveAirport(AirportDTO airport) {
        airport.setAirportId(IDGenerate.airportId());
        return airport;
    }

    @Override
    public AirportDTO getSelectedAirport(String id) {
        return null;
    }

    @Override
    public List<AirportDTO> getAllAirports() {
        return List.of();
    }

    @Override
    public void deleteAirport(String id) {

    }

    @Override
    public void updateAirport(String id, AirportDTO airportDTO) {

    }
}
