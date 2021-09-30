package com.webTechno.hotel.management.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webTechno.hotel.management.repository.RoomJdbcRepository;
import com.webTechno.hotel.management.service.dto.RoomDto;

@Service
public class RoomService {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(RoomService.class);

    private RoomJdbcRepository roomJdbcRepository;

    public RoomService(RoomJdbcRepository roomJdbcRepository) {
        super();
        this.roomJdbcRepository = roomJdbcRepository;
    }

    public List<RoomDto> getAllRooms() {
        return roomJdbcRepository.getAllRooms();
    }

    public void addRoom(int roomNumber, String state, String category) throws Exception {
        roomJdbcRepository.addRoom(roomNumber, state, category);
        LOG.info(category + " / " + state);
    }
}
