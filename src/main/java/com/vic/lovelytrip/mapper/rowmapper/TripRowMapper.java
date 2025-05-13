package com.vic.lovelytrip.mapper.rowmapper;

import com.vic.lovelytrip.entity.TripEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripRowMapper implements RowMapper<TripEntity> {

    @Override
    public TripEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        TripEntity tripEntity = new TripEntity();
        tripEntity.setTitle(rs.getString("title"));
        tripEntity.setDescription(rs.getString("description"));

        return tripEntity;
    }
}
