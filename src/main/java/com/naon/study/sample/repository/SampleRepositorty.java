package com.naon.study.sample.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author yannishin
 */
//@Repository
@Slf4j
public class SampleRepositorty {
    public void doSomthing() {
        log.info("Call Repository");
    }
}
