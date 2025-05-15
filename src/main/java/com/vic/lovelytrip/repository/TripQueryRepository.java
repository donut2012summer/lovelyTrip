package com.vic.lovelytrip.repository;

import com.vic.lovelytrip.dto.TripSearchRequest;
import com.vic.lovelytrip.dto.TripSearchResponse;
import com.vic.lovelytrip.lib.SqlLoader;
import com.vic.lovelytrip.mapper.rowmapper.TripSearchResponseRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TripQueryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;
    private final String querySql;

    public TripQueryRepository(NamedParameterJdbcTemplate jdbcTemplate, SqlLoader sqlLoader) throws Exception{
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
        this.querySql = sqlLoader.load("db_script/trip_search.sql");
    }

    public List<TripSearchResponse> searchTrips(TripSearchRequest request) {

        Map<String, Object> params = new HashMap<>();
        params.put("keyword", request.getKeyword());
        params.put("destinationId", request.getDestinationId());
        params.put("priceMin", request.getPriceMin());
        params.put("priceMax", request.getPriceMax());
        params.put("offset", request.getPage() * request.getSize());
        params.put("size", request.getSize());

        return jdbcTemplate.query(querySql, params, new TripSearchResponseRowMapper());
    }


}
