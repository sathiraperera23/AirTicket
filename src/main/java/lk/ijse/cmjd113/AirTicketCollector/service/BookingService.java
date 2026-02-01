package lk.ijse.cmjd113.AirTicketCollector.service;

import lk.ijse.cmjd113.AirTicketCollector.dto.BookingDTO;

import java.util.List;

public interface BookingService {

    void saveBooking(BookingDTO bookingDTO);
    BookingDTO getBooking(String bookingId);
    List<BookingDTO> getAllBookings();
    void deleteBooking(String bookingId);
    void updateBooking(String bookingId, BookingDTO bookingDTO);
}
