package com.webTechno.hotel.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webTechno.hotel.management.service.RoomService;
import com.webTechno.hotel.management.service.dto.RoomDto;

@CrossOrigin(origins = "http://localhost:8081/", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);

    /** Service regarding the rooms */
    private RoomService roomService;

    /**
     * Controller
     * 
     * @param roomService
     */
    public RoomController(RoomService roomService) {
        super();
        this.roomService = roomService;
    }

    /**
     * API point that will return all the rooms
     * 
     * @return List<RoomDto> All the rooms
     */
    @GetMapping("/")
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    /**
     * API point that wiil create a new room
     * 
     * @param numberRoom
     * @param state
     * @param category
     * @return
     * @throws Exception
     */
    @PostMapping("/")
    public String addRoom(@RequestParam int roomNumber, @RequestParam String state, @RequestParam String category)
            throws Exception {
        roomService.addRoom(roomNumber, state, category);
        return "Success";
    }

}
