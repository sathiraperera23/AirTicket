package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import lk.ijse.cmjd113.AirTicketCollector.dto.BookingDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.BookingService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final List<BookingDTO> bookings = new ArrayList<>();
    private int bookingCounter = 0;

    private String generateBookingId() {
        bookingCounter++;
        return String.format("BKG-%03d", bookingCounter);
    }

    @Override
    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        bookingDTO.setBookingId(generateBookingId());
        bookingDTO.setBooking_date(LocalDateTime.now());
        bookingDTO.setStatus("CONFIRMED");

        bookings.add(bookingDTO);
        return bookingDTO;
    }

    @Override
    public BookingDTO getSelectedBooking(String id) {
        return bookings.stream()
                .filter(b -> b.getBookingId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookings;
    }

    @Override
    public void deleteBooking(String id) {
        BookingDTO booking = getSelectedBooking(id);
        bookings.remove(booking);
    }

    @Override
    public void updateBooking(String id, BookingDTO bookingDTO) {
        BookingDTO existing = getSelectedBooking(id);

        existing.setSeat_count(bookingDTO.getSeat_count());
        existing.setTotal_amt(bookingDTO.getTotal_amt());
        existing.setStatus(bookingDTO.getStatus());
        existing.setUser_id(bookingDTO.getUser_id());
        existing.setFlight_id(bookingDTO.getFlight_id());
    }
}
