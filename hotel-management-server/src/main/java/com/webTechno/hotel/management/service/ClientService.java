package com.webTechno.hotel.management.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webTechno.hotel.management.repository.ClientJdbcRepository;
import com.webTechno.hotel.management.service.dto.ClientDto;

@Service
public class ClientService {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    /** Instance du repository client */
    private ClientJdbcRepository clientJdbcRepository;

    /**
     * Constructeur
     * 
     * @param clientJdbcRepository
     */
    public ClientService(ClientJdbcRepository clientJdbcRepository) {
        super();
        this.clientJdbcRepository = clientJdbcRepository;
    }

    /**
     * Authentification d'un client
     * 
     * @param email
     * @param password
     * @return
     */
    public boolean authenticate(String email, String password) {
        return this.clientJdbcRepository.authenticate(email, password);
    }

    /**
     * Inscription d'un nouveau client
     * 
     * @param newClient
     * @return
     * @throws Exception
     */
    public boolean subscribe(ClientDto newClient) throws Exception {
        return this.clientJdbcRepository.subscribe(newClient);
    }

}