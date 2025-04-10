package com.vic.lovelytrip;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@EnableAspectJAutoProxy
public class ApplicationConfig {
}
