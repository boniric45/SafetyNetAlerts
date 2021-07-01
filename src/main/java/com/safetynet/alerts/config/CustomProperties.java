package com.safetynet.alerts.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "com.safetynet.alerts")
@SpringBootConfiguration

/**
 * Custom Properties path data.json into application.properties
 *
 */
public class CustomProperties {
    private String jsonDatafile;
}
