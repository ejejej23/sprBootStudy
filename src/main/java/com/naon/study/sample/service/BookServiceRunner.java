package com.naon.study.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author yannishin
 */
@Component
public class BookServiceRunner implements ApplicationRunner {
    @Autowired
    BookService bookService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        bookService.printBookRepository();
    }
}
