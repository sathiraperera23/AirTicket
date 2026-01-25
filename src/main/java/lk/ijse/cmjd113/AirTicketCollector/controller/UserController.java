package lk.ijse.cmjd113.AirTicketCollector.controller;

import lk.ijse.cmjd113.AirTicketCollector.dto.UserDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CREATE USER
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                userService.saveUser(userDTO),
                HttpStatus.CREATED
        );
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getSelectedUser(@PathVariable("id") String id) {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(id);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPassword("1234");
        userDTO.setRole(UserDTO.Role.USER);
        userDTO.setPhone("0771234567");

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // GET ALL USERS
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> users = List.of(
                createUser("USR-001", "Admin", "User", "admin@example.com", UserDTO.Role.ADMIN),
                createUser("USR-002", "John", "Doe", "john@example.com", UserDTO.Role.USER),
                createUser("USR-003", "Jane", "Smith", "jane@example.com", UserDTO.Role.USER)
        );

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        System.out.println("Delete User " + id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // UPDATE USER (PATCH)
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(
            @RequestBody UserDTO updatedUser,
            @PathVariable("id") String id
    ) {
        updatedUser.setUserID(id);
        System.out.println("Update User ID " + id);
        System.out.println("Updated User " + updatedUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // helper method (same idea as mock data in AirportController)
    private UserDTO createUser(
            String id,
            String firstName,
            String lastName,
            String email,
            UserDTO.Role role
    ) {
        UserDTO user = new UserDTO();
        user.setUserID(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword("1234");
        user.setRole(role);
        user.setPhone("0770000000");
        return user;
    }
}
