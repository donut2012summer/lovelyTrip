package com.vic.lovelytrip.mapper.rowmapper;

import com.vic.lovelytrip.entity.ImageEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRowMapper implements RowMapper<ImageEntity> {
    @Override
    public ImageEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new ImageEntity();
    }
}
