package com.naon.study.sample.service;

import com.naon.study.sample.repository.SampleRepositorty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yannishin
 */
@Service
@RequiredArgsConstructor
public class SampleService {
    // 1. 생정자 주입
    private SampleRepositorty sampleRepositorty;

    @Autowired
    public SampleService(SampleRepositorty sampleRepositorty) {
        this.sampleRepositorty = sampleRepositorty;
    }

    // 2. 생정자 주입(Lombok)
//    private final SampleRepositorty sampleRepositorty;

    // 3. Setter 주입
//    private SampleRepositorty sampleRepositorty;
//
//    @Autowired
//    public void setSampleRepositorty(SampleRepositorty sampleRepositorty) {
//        this.sampleRepositorty = sampleRepositorty;
//    }

    // 4. Setter 주입(NullPointerException)
//        private SampleRepositorty sampleRepositorty;
//
//        @Autowired(required = false)
//        public void setSampleRepositorty(SampleRepositorty sampleRepositorty) {
//            this.sampleRepositorty = sampleRepositorty;
//        }
//
//        public void callRepository() {
//            sampleRepositorty.doSomthing();
//        }
    // 5. Field 주입
//    @Autowired
//    private SampleRepositorty sampleRepositorty;
}
