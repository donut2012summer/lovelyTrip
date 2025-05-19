package com.vic.lovelytrip.repository.impl;

import com.vic.lovelytrip.common.util.SqlLoader;
import com.vic.lovelytrip.repository.ImageRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepositoryImpl implements ImageRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    private final SqlLoader sqlLoader;

    private final String FILE_NAME = "image";

    private final Logger logger = LoggerFactory.getLogger(ImageRepositoryImpl.class);

    public ImageRepositoryImpl(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    @Override
    public int updateStatusById(int status, Long id) {

        final String QUERY_NAME = "updateStatusById";

        String sql = sqlLoader.getQuery(FILE_NAME, QUERY_NAME)
                .orElseThrow(() -> new IllegalStateException("Required SQL query " + QUERY_NAME + " not found in " + FILE_NAME + ".sql"));

        return jdbcTemplate.update(sql, id, status);
    }
}
