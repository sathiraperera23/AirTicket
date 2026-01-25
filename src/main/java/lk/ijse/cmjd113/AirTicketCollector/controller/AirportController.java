package lk.ijse.cmjd113.AirTicketCollector.controller;

import lk.ijse.cmjd113.AirTicketCollector.dto.AirportDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.AirportService;
import lk.ijse.cmjd113.AirTicketCollector.service.Impl.AirportServiceImpl;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirportDTO> saveAirport(
            @RequestBody AirportDTO airportDTO){
        airportService.saveAirport(airportDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

@GetMapping("/{id}")
public ResponseEntity<AirportDTO> getSelectedAirport(@PathVariable ("id") String id){
       var airportDTO =  new AirportDTO(id,
                "CMB",
                "Bandaranaike International Airport",
                "Colombo",
                "Sri Lanka");

        return new ResponseEntity<>(airportDTO, HttpStatus.OK);
}

@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirportDTO>> getAllAirports(){
    List<AirportDTO> airports = List.of(
            new AirportDTO(
                    "AIR-001",
                    "CMB",
                    "Bandaranaike International Airport",
                    "Colombo",
                    "Sri Lanka"
            ),
            new AirportDTO(
                    "AIR-002",
                    "LHR",
                    "Heathrow Airport",
                    "London",
                    "United Kingdom"
            ),
            new AirportDTO(
                    "AIR-003",
                    "JFK",
                    "John F. Kennedy International Airport",
                    "New York",
                    "United States"
            ),
            new AirportDTO(
                    "AIR-004",
                    "DXB",
                    "Dubai International Airport",
                    "Dubai",
                    "United Arab Emirates"
            ),
            new AirportDTO(
                    "AIR-005",
                    "HND",
                    "Haneda Airport",
                    "Tokyo",
                    "Japan"
            )
    );
return  new ResponseEntity<>(airports, HttpStatus.OK);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteAirport(@PathVariable ("id") String id){
        System.out.println("Delete Airport "+id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@PatchMapping(value = "/{id}", consumes =  MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Void> updateAirports(
        @RequestBody AirportDTO updatedAirport,
        @PathVariable ("id") String id
){
        updatedAirport.setAirportId(id);
        System.out.println("Update Airport ID "+id);
        System.out.println("Updated Airport "+updatedAirport);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}
