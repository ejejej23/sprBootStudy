package com.naon.study.post.controller;

import com.naon.framework.api.response.ResponseHelper;
import com.naon.framework.api.response.ResponseListHelper;
import com.naon.study.post.dto.PostDto;
import com.naon.study.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseHelper<PostDto.Response> createPost(@RequestBody @Valid final PostDto.Create dto) { // 2)
        return ResponseHelper.createOK(postService.createPost(dto));
    }

    @GetMapping("/{id}")
    public ResponseHelper<PostDto.Response> findPost(@PathVariable Long id) {
        return ResponseHelper.createOK(postService.findPost(id));
    }

    @GetMapping
    public ResponseListHelper findPosts(PostDto.Search dto) { // 3)
        return ResponseListHelper.of(postService.findPosts(dto));
    }

    @PutMapping("/{id}")
    public ResponseHelper<PostDto.Response> updatePost(@PathVariable Long id, @RequestBody @Valid PostDto.Update dto) {
        return ResponseHelper.createOK(postService.updatePost(id, dto));
    }

    @PatchMapping("/{id}/title")
    public ResponseHelper<PostDto.Response> updateTitle(@PathVariable Long id, @RequestParam String title ) {
        return ResponseHelper.createOK(postService.updateTitle(id, title));
    }

    @DeleteMapping("/{id}")
    public ResponseHelper<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseHelper.DEFAULT_OK;
    }
}
