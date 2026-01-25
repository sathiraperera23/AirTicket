package lk.ijse.cmjd113.AirTicketCollector.service;

import lk.ijse.cmjd113.AirTicketCollector.dto.BookingDTO;

import java.util.List;

public interface BookingService {

    BookingDTO saveBooking(BookingDTO bookingDTO);

    BookingDTO getSelectedBooking(String id);

    List<BookingDTO> getAllBookings();

    void deleteBooking(String id);

    void updateBooking(String id, BookingDTO bookingDTO);
}
