package com.webTechno.hotel.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081/", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ReservationController.class);
}
