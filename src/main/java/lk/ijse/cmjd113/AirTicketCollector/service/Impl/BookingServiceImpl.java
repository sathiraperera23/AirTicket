package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd113.AirTicketCollector.dao.BookingDAO;
import lk.ijse.cmjd113.AirTicketCollector.dao.FlightDAO;
import lk.ijse.cmjd113.AirTicketCollector.dao.UserDAO;
import lk.ijse.cmjd113.AirTicketCollector.dto.BookingDTO;
import lk.ijse.cmjd113.AirTicketCollector.entities.BookingEntity;
import lk.ijse.cmjd113.AirTicketCollector.exception.DataNotFoundException;
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
    public void saveBooking(BookingDTO bookingDTO) {

        var flight = flightDAO.findById(bookingDTO.getFlightId())
                .orElseThrow(() -> new DataNotFoundException("Flight Not Found"));

        var user = userDAO.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User Not Found"));

        BookingEntity booking = mapper.toBookingEntity(bookingDTO);
        booking.setBookingId(IDGenerate.bookingId());
        booking.setBookingDate(LocalDateTime.now());
        booking.setFlightId(flight);
        booking.setUser(user);

        bookingDAO.save(booking);
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
        bookingDAO.findById(bookingId)
                .orElseThrow(() -> new DataNotFoundException("Booking Not Found"));
        bookingDAO.deleteById(bookingId);
    }

    @Override
    public void updateBooking(String bookingId, BookingDTO bookingDTO) {

        BookingEntity existingBooking = bookingDAO.findById(bookingId)
                .orElseThrow(() -> new DataNotFoundException("Booking Not Found"));

        existingBooking.setSeatCount(bookingDTO.getSeatCount());
        existingBooking.setTotalAmount(bookingDTO.getTotalAmount());
        existingBooking.setStatus(bookingDTO.getStatus());

        bookingDAO.save(existingBooking);
    }
}
