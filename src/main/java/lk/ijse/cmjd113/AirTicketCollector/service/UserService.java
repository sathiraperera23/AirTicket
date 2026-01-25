package lk.ijse.cmjd113.AirTicketCollector.service;

import lk.ijse.cmjd113.AirTicketCollector.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    UserDTO getSelectedUser(String id);

    List<UserDTO> getAllUsers();

    void deleteUser(String id);

    void updateUser(String id, UserDTO userDTO);
}
