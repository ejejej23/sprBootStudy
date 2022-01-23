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
import com.naon.study.post.dto.PostDto;
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
        // Given
        PostDto.Create createDto = PostDto.Create.builder()
            .title("테스트")
            .content("테스트")
            .author("지수")
            .postDt(LocalDateTime.now())
            .build();

        // When
        this.mockMvc.perform(
                post("/api/posts")
                    .content(objectMapper.writeValueAsString(createDto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("포스트조회")
    void findPost() throws Exception {
        // Given
        PostDto.Create createDto = PostDto.Create.builder()
            .title("테스트2")
            .content("테스트2")
            .author("손나은")
            .postDt(LocalDateTime.now())
            .build();

        Long postId = postRepository.save(createDto.toEntity()).getId();

        // When
        this.mockMvc.perform(
                get("/api/posts/{posId}", postId)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("포스트검색")
    void findPosts() throws Exception {
        // Given
        List<Post> posts = Arrays.asList(
            Post.builder()
                .title("테스트1")
                .content("테스트")
                .author("지수")
                .postDt(LocalDateTime.of(2022, 1,15, 0, 0)).build(),
            Post.builder().
                title("테스트2").
                content("테스트").
                author("손나은")
                .postDt(LocalDate.of(2022, 1, 16).atStartOfDay()).build(),
            Post.builder()
                .title("테스트3")
                .content("테스트")
                .author("아이린")
                .postDt(LocalDateTime.now()).build()
        );
        postRepository.saveAll(posts);

        // When
        this.mockMvc.perform(
                get("/api/posts")
                    .param("title", "테스트")
                    .param("postDt", LocalDate.of(2022,1,15).atStartOfDay().format(DateTimeFormatter.ISO_DATE_TIME))
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("포스트갱신")
    void updatePost() throws Exception {
        // Given
        PostDto.Create createDto = PostDto.Create.builder()
            .title("테스트2")
            .content("블랙핑크")
            .author("지수")
            .postDt(LocalDateTime.now())
            .build();

        Long postId = postRepository.save(createDto.toEntity()).getId();

        PostDto.Update updateDto = PostDto.Update.builder()
            .title("테스트")
            .content("블랙핑크")
            .author("제니")
            .build();

        // When
        this.mockMvc.perform(
                put("/api/posts/{posId}", postId)
                    .content(objectMapper.writeValueAsString(updateDto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("포스트의 제목 수정")
    void updateTitle() throws Exception {
        // Given
        PostDto.Create createDto = PostDto.Create.builder()
            .title("테스트3")
            .content("에이핑크")
            .author("손나은")
            .postDt(LocalDateTime.now())
            .build();

        Long postId = postRepository.save(createDto.toEntity()).getId();

        // When
        this.mockMvc.perform(
                patch("/api/posts/{posId}/title", postId)
                    .param("title", "테스트4")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("포스트 삭제")
    void deletePost() throws Exception {
        // Given
        PostDto.Create createDto = PostDto.Create.builder()
            .title("테스트2")
            .content("블랙핑크")
            .author("지수")
            .postDt(LocalDateTime.now())
            .build();

        Long postId = postRepository.save(createDto.toEntity()).getId();

        // When
        this.mockMvc.perform(
                delete("/api/posts/{posId}", postId)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }
}