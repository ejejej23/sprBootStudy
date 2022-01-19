package com.naon.study.profile;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author yannishin
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class ProfileTest {
    @Value("${sample.meaaage}")
    String sampleMessage;

    @Value("${sample.db}")
    String sampleDb;

    @Value("${sample.redis}")
    String sampleRedis;

    @Test
    public void ProfileTest() throws Exception {
        log.info("messages = {}", sampleMessage);
        log.info("db = {}", sampleDb);
        log.info("redis = {}", sampleRedis);
    }
}
