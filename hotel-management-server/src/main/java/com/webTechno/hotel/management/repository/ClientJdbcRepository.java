package com.webTechno.hotel.management.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webTechno.hotel.management.service.dto.ClientDto;

@Repository
public class ClientJdbcRepository extends HotelJdbcRepository {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ClientJdbcRepository.class);

    public ClientJdbcRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public List<Integer> getIdClient(String emailClient) {
        List<Integer> id = new ArrayList<>();
        try {
            String request = "SELECT id FROM client WHERE email = '" + emailClient + "'";
            id = executeQuery(request, (rs, rowNum) -> {
                return rs.getInt("id");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Authentification d'un client en vérifiant la BDD
     * 
     * @param email
     * @param password
     * @return
     */
    public boolean authenticate(String email, String password) {
        List<ClientDto> clients = new ArrayList<>();
        try {
            String request = "SELECT * FROM client WHERE email = '" + email + "' AND password = '" + password + "';";
            clients = executeQuery(request, (rs, rowNum) -> {
                ClientDto client = new ClientDto();
                client.setSurname(rs.getString("surname"));
                client.setLastname(rs.getString("lastname"));
                client.setNationality(rs.getString("nationality"));
                client.setAge(rs.getInt("age"));
                client.setMail(rs.getString("email"));
                client.setPassword("password");
                return client;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (clients.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Inscription d'un nouveau client en BDD
     * 
     * @param newClient
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean subscribe(ClientDto newClient) throws Exception {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String request = "INSERT INTO client (surname, lastname, nationality, age, email, password) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatementCreatorFactory statementCreatorFactory = new PreparedStatementCreatorFactory(request,
                    Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.VARCHAR);
            statementCreatorFactory.setReturnGeneratedKeys(true);
            statementCreatorFactory.setGeneratedKeysColumnNames("id");
            statementCreatorFactory.setResultSetType(Types.BIGINT);
            PreparedStatementCreator preparedStatementCreator = statementCreatorFactory.newPreparedStatementCreator(
                    new Object[] { newClient.getSurname(), newClient.getLastname(), newClient.getNationality(),
                            newClient.getAge(), newClient.getMail(), newClient.getPassword() });
            jdbcTemplate.update(preparedStatementCreator, keyHolder);

//            LOG.info(keyHolder.getKey().toString());

//            return (long) keyHolder.getKey();
            if (keyHolder.getKey() != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Erreur lors de l'insertion d'un client : ", e);
            throw new Exception(e.getMessage());
        }
    }
}
