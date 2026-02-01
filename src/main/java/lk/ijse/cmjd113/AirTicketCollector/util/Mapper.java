package lk.ijse.cmjd113.AirTicketCollector.util;

import lk.ijse.cmjd113.AirTicketCollector.dto.*;
import lk.ijse.cmjd113.AirTicketCollector.entities.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    /* ===================== AIRPORT ===================== */

    public AirportDTO toAirportDTO(AirportEntity airportEntity) {
        return modelMapper.map(airportEntity, AirportDTO.class);
    }

    public AirportEntity toAirportEntity(AirportDTO airportDTO) {
        return modelMapper.map(airportDTO, AirportEntity.class);
    }

    public List<AirportDTO> toAirportDTOList(List<AirportEntity> airportEntityList) {
        return modelMapper.map(
                airportEntityList,
                new TypeToken<List<AirportDTO>>() {}.getType()
        );
    }

    /* ===================== FLIGHT ===================== */

    public FlightDTO toFlightDTO(FlightEntity flightEntity) {
        return modelMapper.map(flightEntity, FlightDTO.class);
    }

    public FlightEntity toFlightEntity(FlightDTO flightDTO) {
        return modelMapper.map(flightDTO, FlightEntity.class);
    }

    public List<FlightDTO> toFlightDTOList(List<FlightEntity> flightEntityList) {
        return modelMapper.map(
                flightEntityList,
                new TypeToken<List<FlightDTO>>() {}.getType()
        );
    }

    /* ===================== USER ===================== */

    public UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO dto = modelMapper.map(userEntity, UserDTO.class);

        // 🔐 IMPORTANT: never expose password
        dto.setPassword(null);

        return dto;
    }

    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public List<UserDTO> toUserDTOList(List<UserEntity> userEntityList) {
        return modelMapper.map(
                userEntityList,
                new TypeToken<List<UserDTO>>() {}.getType()
        );
    }
}
