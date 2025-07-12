package com.vic.lovelytrip.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(SqlLoader.class);

    public SqlLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Retrieves a SQL query string by its name from a cached or resource-based .sql file.
     *
     * @param filename   the name of the SQL file (without the `.sql` extension) located in the `db_script/` folder.
     * @param queryName  the name of the query within the file, identified by `-- name:<queryName>` tag.
     * @return           an {@code Optional<String>} containing the SQL query if found; otherwise {@code Optional.empty()}.
     */
    public Optional<String> getQuery(String filename, String queryName) {

        try{

            Map<String, String> queries = fileQueryCache.get(filename);

            if (queries == null) {
                queries = getQueryFromResource(filename);
                fileQueryCache.put(filename, queries);
            }

            return Optional.ofNullable(queries.get(queryName));

        }catch (IOException e){
            logger.error("Failed to load SQL file '{}': {}", filename, e.getMessage());
            return Optional.empty();
        }

    }

    /**
     * Loads a SQL file from the `db_script/` resource folder and parses it into a map of named SQL queries.
     *
     * @param filename  the name of the SQL file (without `.sql` extension) to load from classpath.
     * @return          a map where keys are query names and values are the associated SQL statements.
     * @throws IOException if the file cannot be read.
     * @throws IllegalArgumentException if the SQL file does not exist in the resource path.
     */
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

    /**
     * Parses lines from a SQL file and maps each named query to its SQL content.
     * Query sections are defined by lines starting with `-- name:<queryName>`.
     *
     * @param lines  the list of lines read from the SQL file.
     * @return       a map of query names to their corresponding SQL statements.
     */
    private Map<String, String> parseFileIntoQueryMap(List<String> lines) {

        String queryName = null;

        StringBuilder querySqlBuilder = new StringBuilder();

        // queryMap
        Map<String, String> queryMap = new HashMap<>();

        final String queryPrefix = "-- name:";

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith(queryPrefix)) {

                if (queryName != null) {
                    queryMap.put(queryName, querySqlBuilder.toString().trim());
                    // reset builder after save to map
                    querySqlBuilder.setLength(0);
                }

                // extract queryName from line
                queryName = line.substring(queryPrefix.length()).trim();

            }else if (!line.isEmpty()){
                // build sql query
                querySqlBuilder.append(line).append(" ");
            }
        }

        // handle last block of query when end loop
        if (queryName != null) {
            queryMap.put(queryName, querySqlBuilder.toString().trim());
        }

        return queryMap;
    }
}
