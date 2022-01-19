package com.naon.study.sample.service;

import com.naon.study.sample.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author yannishin
 */
@Service
public class BookService {
    // 1, @Qualifier : 빈 이름으로 주입
    @Autowired
    @Qualifier("myBookRepository")
    private BookRepository bookRepository;

    // 2. 해당 타입의 빈 모두 주입받기
//    @Autowired
//    private List<BookRepository> bookRepository;
//
//    public void printBookBookRepository() {
//        this.bookRepository.forEach(System.out::println);
//    }

    // 3. 이름으로
//    @Autowired
//    private BookRepository myBookRepository;

    public void printBookRepository() {
        System.out.println(bookRepository.getClass());
    }
}
