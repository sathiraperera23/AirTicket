package lk.ijse.cmjd113.AirTicketCollector.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd113.AirTicketCollector.dao.UserDAO;
import lk.ijse.cmjd113.AirTicketCollector.dto.UserDTO;
import lk.ijse.cmjd113.AirTicketCollector.entities.UserEntity;
import lk.ijse.cmjd113.AirTicketCollector.exception.DataNotFoundException;
import lk.ijse.cmjd113.AirTicketCollector.service.UserService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import lk.ijse.cmjd113.AirTicketCollector.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;
    private final Mapper mapper;

    @Override
    public void saveUser(UserDTO userDTO) {

        if (userDao.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity userEntity = mapper.toUserEntity(userDTO);
        userEntity.setUserId(IDGenerate.userId());

        userDao.save(userEntity);
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {

        UserEntity existingUser = userDao.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User Not Found"));

        existingUser.setFullName(userDTO.getFullName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setRole(userDTO.getRole());

        // Update password only if provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(userDTO.getPassword());
        }

        userDao.save(existingUser);
    }

    @Override
    public void deleteUser(String userId) {
        userDao.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User Not Found"));

        userDao.deleteById(userId);
    }

    @Override
    public UserDTO getUser(String userId) {
        UserEntity userEntity = userDao.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User Not Found"));

        return mapper.toUserDTO(userEntity);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapper.toUserDTOList(userDao.findAll());
    }
}
