package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import lk.ijse.cmjd113.AirTicketCollector.dto.UserDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.UserService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final List<UserDTO> users = new ArrayList<>();
    private int userCounter = 0;

    private String generateUserId() {
        userCounter++;
        return String.format("USR-%03d", userCounter);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        userDTO.setUserID(generateUserId());
        users.add(userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getSelectedUser(String id) {
        return users.stream()
                .filter(user -> user.getUserID().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return users;
    }

    @Override
    public void deleteUser(String id) {
        UserDTO user = getSelectedUser(id);
        users.remove(user);
    }

    @Override
    public void updateUser(String id, UserDTO userDTO) {
        UserDTO existingUser = getSelectedUser(id);

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setRole(userDTO.getRole());
    }
}
