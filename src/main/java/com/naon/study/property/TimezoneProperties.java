package com.naon.study.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yannishin
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix="app-config.timezone")
public class TimezoneProperties {
    private String defaultTimezone;
    private String convertTimezone;
}








