package lk.ijse.cmjd113.AirTicketCollector.service;

import lk.ijse.cmjd113.AirTicketCollector.dto.PassengerDTO;

import java.util.List;

public interface PassengerService {

    PassengerDTO savePassenger(PassengerDTO passengerDTO);

    PassengerDTO getSelectedPassenger(String id);

    List<PassengerDTO> getAllPassengers();

    void deletePassenger(String id);

    void updatePassenger(String id, PassengerDTO passengerDTO);
}
