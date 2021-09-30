package com.webTechno.hotel.management.service.dto;

import java.time.LocalDate;

public class ReservationDto {
    private double price;
    private LocalDate dateReservation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private RoomDto chambre;
    private ClientDto client;
}
