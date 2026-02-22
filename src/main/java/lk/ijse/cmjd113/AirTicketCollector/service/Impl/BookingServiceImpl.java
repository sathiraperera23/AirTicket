package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd113.AirTicketCollector.dao.BookingDAO;
import lk.ijse.cmjd113.AirTicketCollector.dao.FlightDAO;
import lk.ijse.cmjd113.AirTicketCollector.dao.UserDAO;
import lk.ijse.cmjd113.AirTicketCollector.dto.BookingDTO;
import lk.ijse.cmjd113.AirTicketCollector.entities.BookingEntity;
import lk.ijse.cmjd113.AirTicketCollector.exception.DataNotFoundException;
import lk.ijse.cmjd113.AirTicketCollector.exception.DataSaveException;
import lk.ijse.cmjd113.AirTicketCollector.service.BookingService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import lk.ijse.cmjd113.AirTicketCollector.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingDAO bookingDAO;
    private final FlightDAO flightDAO;
    private final UserDAO userDAO;
    private final Mapper mapper;


    @Override
    public void saveBooking(BookingDTO booking) {

        var foundFlight = flightDAO.findById(booking.getFlightId())
                .orElseThrow(() -> new DataNotFoundException("Flight not found"));

        var foundUser = userDAO.findById(booking.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        var bookingEntity = mapper.toBookingEntity(booking);
        bookingEntity.setFlightId(foundFlight);
        bookingEntity.setUser(foundUser);
        bookingEntity.setBookingId(IDGenerate.bookingId());

        //Todo:Update seat count
        var availableSeats = flightDAO.getAvailableSeats(booking.getFlightId());
        if(availableSeats == 0 || availableSeats < booking.getSeatCount() ){
            throw new DataSaveException("No available seats found or exceed the seat limit");
        }
        bookingDAO.save(bookingEntity);
        flightDAO.deductAvlSeats(booking.getSeatCount(),booking.getFlightId());
    }

    @Override
    public BookingDTO getBooking(String bookingId) {
        return mapper.toBookingDTO(
                bookingDAO.findById(bookingId)
                        .orElseThrow(() -> new DataNotFoundException("Booking Not Found"))
        );
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return mapper.toBookingDTOList(bookingDAO.findAll());
    }

    @Override
    public void deleteBooking(String bookingId) {
        var foundBooking = bookingDAO.findById(bookingId)
                .orElseThrow(() -> new DataNotFoundException("Booking not found"));
        bookingDAO.deleteById(bookingId);
        flightDAO.addAvlSeats(foundBooking.getSeatCount(),foundBooking.getFlightId().getFlightNo());
    }


    @Override
    public void updateBooking(String bookingId, BookingDTO booking) {
        var foundBooking = bookingDAO.findById(bookingId)
                .orElseThrow(() -> new DataNotFoundException("Booking not found"));

        var foundFlight = flightDAO.findById(booking.getFlightId())
                .orElseThrow(() -> new DataNotFoundException("Flight not found"));

        var foundUser = userDAO.findById(booking.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        //Handle seat avilabbility
        var newSeatCount = booking.getSeatCount();
        var prevSeatCount = foundBooking.getSeatCount();
        var seatCountDiff = newSeatCount - prevSeatCount;
        // 5 - 2 = 3
        // 5 - 8 = -3

        if(seatCountDiff > 0){
            flightDAO.deductAvlSeats(seatCountDiff,booking.getFlightId());
        }else {
            flightDAO.addAvlSeats(Math.abs(seatCountDiff),booking.getFlightId());
        }
        foundBooking.setStatus(booking.getStatus());
        foundBooking.setBookingDate(booking.getBookingDate());
        foundBooking.setSeatCount(booking.getSeatCount());
        foundBooking.setTotalAmount(booking.getTotalAmount());
        foundBooking.setFlightId(foundFlight);
        foundBooking.setUser(foundUser);
    }
}
