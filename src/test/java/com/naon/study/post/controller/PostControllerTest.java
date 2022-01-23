package com.naon.study.post.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naon.study.post.domain.Post;
import com.naon.study.post.domain.repositopry.PostRepository;
import com.naon.study.post.service.PostService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@CustomAutoConfigureMockMvc
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PostControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("포스트 등록")
    void createPost() throws Exception {

    }

    @Test
    @DisplayName("포스트조회")
    void findPost() throws Exception {

    }

    @Test
    @DisplayName("포스트검색")
    void findPosts() throws Exception {
    }

    @Test
    @DisplayName("포스트갱신")
    void updatePost() throws Exception {

    }

    @Test
    @DisplayName("포스트의 제목 수정")
    void updateTitle() throws Exception {

    }

    @Test
    @DisplayName("포스트 삭제")
    void deletePost() throws Exception {
    }
}