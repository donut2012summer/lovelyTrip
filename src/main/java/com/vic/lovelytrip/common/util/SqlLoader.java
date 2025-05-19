package com.vic.lovelytrip.common.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SqlLoader {

    private final ResourceLoader resourceLoader;

    // Caches all queries loaded per file to avoid repeated reads
    private final Map<String, Map<String, String>> fileQueryCache = new ConcurrentHashMap<>();

    public SqlLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Optional<String> getQuery(String filename, String queryName) {

        try{

            Map<String, String> queries = fileQueryCache.get(filename);

            if (queries == null) {
                queries = getQueryFromResource(filename);
                fileQueryCache.put(filename, queries);
            }

            return Optional.ofNullable(queries.get(queryName));

        }catch (IOException e){
            return Optional.empty();
        }

    }

    private Map<String, String> getQueryFromResource(String filename) throws IOException {

        final String PARENT_FOLDER = "db_script/";

        String filePath = PARENT_FOLDER + filename + ".sql";

        Resource resource = resourceLoader.getResource("classpath:" + filePath);

        if (!resource.exists()) {
            throw new IllegalArgumentException("Sql file not found: " + filePath);
        }

        // get file from system
        List<String> lines = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8);

        return parseFileIntoQueryMap(lines);
    }


    private Map<String, String> parseFileIntoQueryMap(List<String> lines) {

        // queryName
        String queryName = null;

        // querySqlBuilder
        StringBuilder queryBuilder = new StringBuilder();

        // queryMap
        Map<String, String> queryMap = new HashMap<>();

        final String queryPrefix = "-- name:";

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith(queryPrefix)) {

                if (queryName != null) {
                    queryMap.put(queryName, queryBuilder.toString().trim());
                    // reset builder after save to map
                    queryBuilder.setLength(0);
                }

                // extract queryName from line
                queryName = line.substring(queryPrefix.length()).trim();

            }else if (!line.isEmpty()){
                // build sql query
                queryBuilder.append(line).append(" ");
            }
        }

        // put last block of query after loop
        if (queryName != null) {
            queryMap.put(queryName, queryBuilder.toString().trim());
        }

        return queryMap;
    }
}
