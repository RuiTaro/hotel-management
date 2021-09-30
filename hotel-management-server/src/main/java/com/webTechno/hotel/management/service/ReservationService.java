package com.webTechno.hotel.management.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webTechno.hotel.management.repository.ReservationJdbcRepository;

@Service
public class ReservationService {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ReservationService.class);

    private ReservationJdbcRepository reservationJdbcRepository;

    public ReservationService(ReservationJdbcRepository reservationJdbcRepository) {
        super();
        this.reservationJdbcRepository = reservationJdbcRepository;
    }

}
