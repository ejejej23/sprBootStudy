package com.naon.study;

import com.naon.extern.ExternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
//@SpringBootApplication(scanBasePackageClasses = SpringBootStudyApplication.class)
//@SpringBootApplication(scanBasePackages = {"com.naon"})
public class SpringBootStudyApplication {
    //@Autowired
//    ExternService externService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);

//        SpringApplication app = new SpringApplication(SpringBootStudyApplication.class);
//        app.addInitializers((ApplicationContextInitializer<GenericApplicationContext>) ctx -> {
//            ctx.registerBean(ExternService.class);
//            // 메시지를 출력하는 ApplicationRunner를 생성해서 빈으로 등록
//            ctx.registerBean(ApplicationRunner.class, () -> args1 -> System.out.println("Hello World!!"));
//        });
//
//        app.run(args);

    }
}
