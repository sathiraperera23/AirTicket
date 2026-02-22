package lk.ijse.cmjd113.AirTicketCollector.dao;

import lk.ijse.cmjd113.AirTicketCollector.entities.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerDAO extends JpaRepository<PassengerEntity,String> {
}
