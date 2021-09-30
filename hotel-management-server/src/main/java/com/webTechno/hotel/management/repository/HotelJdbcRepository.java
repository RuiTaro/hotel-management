package com.webTechno.hotel.management.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public abstract class HotelJdbcRepository {
    /** Logger */
    private static final Logger LOG = LoggerFactory.getLogger(HotelJdbcRepository.class);

    /** jdbc template */
    protected JdbcTemplate jdbcTemplate;

    /**
     * Constructeur
     * 
     * @param jdbcTemplate
     */
    public HotelJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    protected <T> List<T> executeQuery(String sql, @Nullable Object[] args, RowMapper<T> rowMapper) throws Exception {
        try {
            LOG.info(String.format("execute query %s with parameters [ %s ] ", sql,
                    Arrays.asList(args).stream().map(o -> {
                        return o.toString();
                    }).collect(Collectors.joining(","))));
            return jdbcTemplate.query(sql, args, rowMapper);
        } catch (Exception e) {
            LOG.error(String.format("error while executing sql request : %s with error %s", sql, e.getMessage()), e);
            throw new Exception(
                    String.format("error while executing sql request : %s with error %s", sql, e.getMessage()));
        }
    }

    protected <T> List<T> executeQuery(String sql, RowMapper<T> rowMapper) throws Exception {
        return executeQuery(sql, new Object[0], rowMapper);
    }

}
