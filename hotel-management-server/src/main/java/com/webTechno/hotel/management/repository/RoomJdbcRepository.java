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

import com.webTechno.hotel.management.service.dto.RoomDto;

@Repository
public class RoomJdbcRepository extends HotelJdbcRepository {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(RoomJdbcRepository.class);

    public RoomJdbcRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public List<RoomDto> getAllRooms() {
        List<RoomDto> rooms = new ArrayList<>();
        try {
            String request = "SELECT * FROM room;";
            rooms = executeQuery(request, (rs, rowNum) -> {
                RoomDto room = new RoomDto();
                room.setNumberRoom(rs.getInt("room_number"));
                room.setState(rs.getString("state"));
                room.setCategory(rs.getString("category"));
                return room;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public List<Integer> getIdRoom(RoomDto room) {
        List<Integer> id = new ArrayList<>();
        try {
            String request = "SELECT id FROM room WHERE room_number = " + room.getNumberRoom() + " AND state = '"
                    + room.getState() + "' AND category = '" + room.getCategory() + "';";
            id = executeQuery(request, (rs, rowNum) -> {
                return rs.getInt("id");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Add a new room in database
     * 
     * @param roomNumber The number of the room
     * @param state      The state of the room
     * @param category   The category of the room
     * @throws Exception
     */
    @Transactional
    public void addRoom(int roomNumber, String state, String category) throws Exception {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String request = "INSERT INTO room (room_number, state, category) VALUES (?, ?, ?);";
            PreparedStatementCreatorFactory statementCreatorFactory = new PreparedStatementCreatorFactory(request,
                    Types.BIGINT, Types.VARCHAR, Types.VARCHAR);
            statementCreatorFactory.setReturnGeneratedKeys(true);
            statementCreatorFactory.setGeneratedKeysColumnNames("id");
            statementCreatorFactory.setResultSetType(Types.BIGINT);
            PreparedStatementCreator preparedStatementCreator = statementCreatorFactory
                    .newPreparedStatementCreator(new Object[] { roomNumber, state, category });
            jdbcTemplate.update(preparedStatementCreator, keyHolder);

//            LOG.info(keyHolder.getKey().toString());

//            return (long) keyHolder.getKey();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Erreur lors de l'insertion d'une chambre : ", e);
            throw new Exception(e.getMessage());
        }
    }

}
