package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd113.AirTicketCollector.dao.BookingDAO;
import lk.ijse.cmjd113.AirTicketCollector.dao.PassengerDAO;
import lk.ijse.cmjd113.AirTicketCollector.dto.PassengerDTO;
import lk.ijse.cmjd113.AirTicketCollector.entities.BookingEntity;
import lk.ijse.cmjd113.AirTicketCollector.entities.PassengerEntity;
import lk.ijse.cmjd113.AirTicketCollector.exception.DataNotFoundException;
import lk.ijse.cmjd113.AirTicketCollector.service.PassengerService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import lk.ijse.cmjd113.AirTicketCollector.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ServiceTwo")
@Transactional
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerDAO passengerDAO;
    private final BookingDAO bookingDAO;
    private final Mapper mapper;

    @Override
    public PassengerDTO savePassenger(PassengerDTO passengerDTO) {
        BookingEntity booking = bookingDAO.findById(passengerDTO.getBookingId())
                .orElseThrow(() -> new DataNotFoundException("Booking Not Found"));

        PassengerEntity passenger = mapper.toPassengerEntity(passengerDTO);
        passenger.setPassengerId(IDGenerate.passengerId());
        passenger.setBooking(booking);

        passengerDAO.save(passenger);

        return mapper.toPassengerDTO(passenger);
    }

    @Override
    public PassengerDTO getSelectedPassenger(String passengerId) {
        PassengerEntity passenger = passengerDAO.findById(passengerId)
                .orElseThrow(() -> new DataNotFoundException("Passenger Not Found"));
        return mapper.toPassengerDTO(passenger);
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        return mapper.toPassengerDTOList(passengerDAO.findAll());
    }

    @Override
    public void deletePassenger(String passengerId) {
        passengerDAO.findById(passengerId)
                .orElseThrow(() -> new DataNotFoundException("Passenger Not Found"));
        passengerDAO.deleteById(passengerId);
    }

    @Override
    public void updatePassenger(String passengerId, PassengerDTO passengerDTO) {
        PassengerEntity existingPassenger = passengerDAO.findById(passengerId)
                .orElseThrow(() -> new DataNotFoundException("Passenger Not Found"));

        if (passengerDTO.getBookingId() != null) {
            BookingEntity booking = bookingDAO.findById(passengerDTO.getBookingId())
                    .orElseThrow(() -> new DataNotFoundException("Booking Not Found"));
            existingPassenger.setBooking(booking);
        }

        existingPassenger.setFirstName(passengerDTO.getFirstName());
        existingPassenger.setLastName(passengerDTO.getLastName());
        existingPassenger.setAge(passengerDTO.getAge());
        existingPassenger.setGender(passengerDTO.getGender());
        existingPassenger.setContactNumber(passengerDTO.getContactNumber());
        existingPassenger.setSeatNumber(passengerDTO.getSeatNumber());

        passengerDAO.save(existingPassenger);
    }
}
