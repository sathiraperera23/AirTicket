package lk.ijse.cmjd113.AirTicketCollector.dao;

import lk.ijse.cmjd113.AirTicketCollector.entities.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDAO extends JpaRepository<AirportEntity,String> {

}
