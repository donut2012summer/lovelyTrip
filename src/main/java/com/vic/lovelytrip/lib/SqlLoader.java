package com.vic.lovelytrip.lib;

import com.vic.lovelytrip.entity.TripEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SqlLoader {

    private final ResourceLoader resourceLoader;
    private final Map<String, String> sqlCache = new ConcurrentHashMap<>();

    public SqlLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String load(String path) throws IOException {
        if (sqlCache.containsKey(path)) {
            return sqlCache.get(path);
        }
        Resource resource = resourceLoader.getResource("classpath:"+path);
        String sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        sqlCache.put(path, sql);
        return sql;

    }
}
