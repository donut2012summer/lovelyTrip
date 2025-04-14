package com.vic.lovelytrip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@EnableAspectJAutoProxy
public class ApplicationConfig {

    @Bean
    public DateTimeProvider dateTimeProvider() {
        return ()-> Optional.of(OffsetDateTime.now());
    }
}
