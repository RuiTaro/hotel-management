package com.webTechno.hotel.management.repository;

import java.sql.Types;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import com.webTechno.hotel.management.service.dto.RoomDto;

@Repository
public class ReservationJdbcRepository extends HotelJdbcRepository {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ReservationJdbcRepository.class);

    private RoomJdbcRepository roomJdbcRepository;

    private ClientJdbcRepository clientJdbcRepository;

    public ReservationJdbcRepository(JdbcTemplate jdbcTemplate, RoomJdbcRepository roomJdbcRepository,
            ClientJdbcRepository clientJdbcRepository) {
        super(jdbcTemplate);
        this.roomJdbcRepository = roomJdbcRepository;
        this.clientJdbcRepository = clientJdbcRepository;
    }

    public boolean bookRoom(RoomDto roomToBook, String emailClient, LocalDate dateDebut, LocalDate dateFin)
            throws Exception {

        try {
            int price = 50;
            int idRoom = this.roomJdbcRepository.getIdRoom(roomToBook).get(0);
            int idClient = this.clientJdbcRepository.getIdClient(emailClient).get(0);
//            KeyHolder keyHolder = new GeneratedKeyHolder();
            String request = "INSERT INTO reservation (price, date_reservation, date_debut, date_fin, room_id, client_id) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatementCreatorFactory statementCreatorFactory = new PreparedStatementCreatorFactory(request,
                    Types.BIGINT, Types.TIMESTAMP, Types.TIMESTAMP, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT);
//            statementCreatorFactory.setReturnGeneratedKeys(true);
//            statementCreatorFactory.setGeneratedKeysColumnNames("id");
            statementCreatorFactory.setResultSetType(Types.BIGINT);
            PreparedStatementCreator preparedStatementCreator = statementCreatorFactory.newPreparedStatementCreator(
                    new Object[] { price, LocalDate.now(), dateDebut, dateFin, idRoom, idClient });
            jdbcTemplate.update(preparedStatementCreator);

//            LOG.info(keyHolder.getKey().toString());

//            return (long) keyHolder.getKey();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Erreur lors de l'insertion d'une chambre : ", e);
            throw new Exception(e.getMessage());
        }

        return false;
    }

}
