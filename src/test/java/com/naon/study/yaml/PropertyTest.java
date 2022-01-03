package com.naon.study.yaml;

import static org.assertj.core.api.Assertions.*;

import com.naon.study.property.JwtProperties;
import com.naon.study.property.TimezoneProperties;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author yannishin
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PropertyTest {
    @Autowired
    TimezoneProperties timezoneProperties;

    @Autowired
    JwtProperties jwtProperties;

    @Test
    public void propertyMapping01() throws Exception {
        assertThat(timezoneProperties.getDefaultTimezone()).isEqualTo("UTC");
    }

    @Test
    @DisplayName("시간 단위로 매핑")
    public void propertyMapping02() throws Exception {
        assertThat(jwtProperties.getTokenExpirationTime()).isEqualTo(Duration.ofHours(8));
        assertThat(jwtProperties.getRefreshTokenExpirationTime()).isEqualTo(Duration.ofHours(48));
    }
}
