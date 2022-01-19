package com.naon.study.sample.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleServiceTest {
    @Autowired
    SampleService sampleService;

    @Test
    @DisplayName("서터주입시 repository가 bean으로 동록되지 않을 경유 예외")
    void doSomeThing() throws Exception {
        //sampleService.callRepository();
    }
}