package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import lk.ijse.cmjd113.AirTicketCollector.dto.AirportDTO;
import lk.ijse.cmjd113.AirTicketCollector.dto.PassengerDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.PassengerService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PassengerServiceTwo implements PassengerService {
    @Override
    public PassengerDTO savePassenger(PassengerDTO passenger) {
        System.out.println(passenger);
        return null;
    }

    @Override
    public PassengerDTO getSelectedPassenger(String airportId) {
        return null;
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        return List.of();
    }

    @Override
    public void deletePassenger(String passengerId) {

    }

    @Override
    public void updatePassenger(String passengerId, PassengerDTO updatePassenger) {

    }
}