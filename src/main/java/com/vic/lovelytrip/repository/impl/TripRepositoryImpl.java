package com.vic.lovelytrip.repository.impl;

import com.vic.lovelytrip.common.util.SqlLoader;
import com.vic.lovelytrip.dto.TripSearchRequest;
import com.vic.lovelytrip.dto.TripSearchResponse;
import com.vic.lovelytrip.mapper.rowmapper.TripSearchResponseRowMapper;
import com.vic.lovelytrip.repository.TripRepositoryCustom;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TripRepositoryImpl implements TripRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    private final SqlLoader sqlLoader;

    private final String FILE_NAME = "trip";

    public TripRepositoryImpl(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    @Override
    public List<TripSearchResponse> searchTrips(TripSearchRequest request) {

        final String QUERY_NAME = "searchTrips";

        Map<String, Object> params = new HashMap<>();

        params.put("keyword", request.getKeyword());
        params.put("destinationId", request.getDestinationId());
        params.put("priceMin", request.getPriceMin());
        params.put("priceMax", request.getPriceMax());
        params.put("offset", request.getPage() * request.getSize());
        params.put("size", request.getSize());

        String sql = sqlLoader.getQuery(FILE_NAME, QUERY_NAME)
                .orElseThrow(() -> new IllegalStateException("Required SQL query" + QUERY_NAME + "not found in" + FILE_NAME + ".sql"));


        return jdbcTemplate.query(sql, new TripSearchResponseRowMapper(), params);
    }
}
