package com.vic.lovelytrip.mapper.rowmapper;

import com.vic.lovelytrip.dto.TripSearchResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripSearchResponseRowMapper implements RowMapper<TripSearchResponse> {

    @Override
    public TripSearchResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        TripSearchResponse response = new TripSearchResponse();
        response.setTripId(rs.getLong("id"));
        response.setTitle(rs.getString("title"));
        response.setUnitPriceMin(rs.getInt("unit_price"));
        response.setImageUrl(rs.getString("image_url"));
        response.setLocationId(rs.getLong("location_id"));
        response.setLocationName(rs.getString("location_name"));

        return null;
    }
}
