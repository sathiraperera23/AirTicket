package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd113.AirTicketCollector.dao.AirportDAO;
import lk.ijse.cmjd113.AirTicketCollector.dto.AirportDTO;
import lk.ijse.cmjd113.AirTicketCollector.entities.AirportEntity;
import lk.ijse.cmjd113.AirTicketCollector.service.AirportService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import lk.ijse.cmjd113.AirTicketCollector.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportServiceImpl implements AirportService {
    private final AirportDAO airportDAO;
    private final Mapper mapper;
    @Override
    public void saveAirport(AirportDTO airport) {
        airport.setAirportId(IDGenerate.airportId());
        AirportEntity airportEntity = mapper.toAirportEntity(airport);
        airportDAO.save(airportEntity);
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
