package com.naon.study.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author yannishin
 */
@Getter
@Setter
@Valid
@Configuration
@ConfigurationProperties(prefix="app-config.jwt")
public class JwtProperties {
    private String base64Secret;
    /**
     * JWT Token 만료 시간
     */
    @DurationUnit(ChronoUnit.HOURS)
    @Min(1)
    @Max(48)
    private Duration tokenExpirationTime = Duration.ofHours(8);
    @DurationUnit(ChronoUnit.HOURS)
    private Duration refreshTokenExpirationTime = Duration.ofHours(8);
}
