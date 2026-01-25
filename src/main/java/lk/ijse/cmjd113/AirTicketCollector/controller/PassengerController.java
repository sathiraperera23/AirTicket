package lk.ijse.cmjd113.AirTicketCollector.controller;

import lk.ijse.cmjd113.AirTicketCollector.dto.PassengerDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passengers")
//@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

     public PassengerController(@Qualifier ("ServiceTwo") PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    // CREATE PASSENGER
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PassengerDTO> savePassenger(@RequestBody PassengerDTO passengerDTO) {
        return new ResponseEntity<>(
                passengerService.savePassenger(passengerDTO),
                HttpStatus.CREATED
        );
    }

    // GET PASSENGER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PassengerDTO> getSelectedPassenger(@PathVariable String id) {
        return ResponseEntity.ok(passengerService.getSelectedPassenger(id));
    }

    // GET ALL PASSENGERS
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    // DELETE PASSENGER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable String id) {
        passengerService.deletePassenger(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // UPDATE PASSENGER
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePassenger(
            @RequestBody PassengerDTO passengerDTO,
            @PathVariable String id
    ) {
        passengerService.updatePassenger(id, passengerDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
