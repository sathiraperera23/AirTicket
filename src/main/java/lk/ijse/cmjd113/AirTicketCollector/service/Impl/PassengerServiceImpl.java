package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import lk.ijse.cmjd113.AirTicketCollector.dto.PassengerDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.PassengerService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class PassengerServiceImpl implements PassengerService {

    private final List<PassengerDTO> passengers = new ArrayList<>();
    private int passengerCounter = 0;

    private String generatePassengerId() {
        passengerCounter++;
        return String.format("PSG-%03d", passengerCounter);
    }

    @Override
    public PassengerDTO savePassenger(PassengerDTO passengerDTO) {
        passengerDTO.setPassenger_id(generatePassengerId());
        passengers.add(passengerDTO);
        return passengerDTO;
    }

    @Override
    public PassengerDTO getSelectedPassenger(String id) {
        return passengers.stream()
                .filter(p -> p.getPassenger_id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        return passengers;
    }

    @Override
    public void deletePassenger(String id) {
        PassengerDTO passenger = getSelectedPassenger(id);
        passengers.remove(passenger);
    }

    @Override
    public void updatePassenger(String id, PassengerDTO passengerDTO) {
        PassengerDTO existing = getSelectedPassenger(id);

        existing.setFirst_name(passengerDTO.getFirst_name());
        existing.setLast_name(passengerDTO.getLast_name());
        existing.setAge(passengerDTO.getAge());
        existing.setGender(passengerDTO.getGender());
        existing.setContact_number(passengerDTO.getContact_number());
        existing.setSeat_number(passengerDTO.getSeat_number());
        existing.setBooking_id(passengerDTO.getBooking_id());
    }
}
