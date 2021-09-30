package com.webTechno.hotel.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webTechno.hotel.management.service.ClientService;
import com.webTechno.hotel.management.service.dto.ClientDto;

@CrossOrigin(origins = "http://localhost:8081/", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    /** Instance du service client */
    private ClientService clientService;

    /**
     * Constructeur
     * 
     * @param clientService
     */
    public ClientController(ClientService clientService) {
        super();
        this.clientService = clientService;
    }

    /**
     * Point API d'authentification
     * 
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/")
    public Boolean authenticate(@RequestParam String email, @RequestParam String password) {
        return this.clientService.authenticate(email, password);
    }

    /**
     * Point API d'inscription
     * 
     * @param newClient
     * @return
     * @throws Exception
     */
    @PostMapping("/")
    public Boolean subscribe(@RequestBody ClientDto newClient) throws Exception {
        return this.clientService.subscribe(newClient);
    }
}
