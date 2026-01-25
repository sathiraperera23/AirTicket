package lk.ijse.cmjd113.AirTicketCollector.controller;

import lk.ijse.cmjd113.AirTicketCollector.dto.FlightDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    // CREATE FLIGHT
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FlightDTO> saveFlight(@RequestBody FlightDTO flightDTO) {
        return new ResponseEntity<>(
                flightService.saveFlight(flightDTO),
                HttpStatus.CREATED
        );
    }

    // GET FLIGHT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> getSelectedFlight(@PathVariable String id) {
        return ResponseEntity.ok(flightService.getSelectedFlight(id));
    }

    // GET ALL FLIGHTS
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    // DELETE FLIGHT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // UPDATE FLIGHT
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateFlight(
            @RequestBody FlightDTO flightDTO,
            @PathVariable String id
    ) {
        flightService.updateFlight(id, flightDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
