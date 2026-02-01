package lk.ijse.cmjd113.AirTicketCollector.dao;

import lk.ijse.cmjd113.AirTicketCollector.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
