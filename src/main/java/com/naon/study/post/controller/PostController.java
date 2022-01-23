/*
 * Naonsoft Inc., Software License, Version 8.0
 * Copyright (c) 2007-2021 Naonsoft Inc.,
 * All rights reserved.
 *
 * DON'T COPY OR REDISTRIBUTE THIS SOURCE CODE WITHOUT PERMISSION.
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <<Naonsoft Inc.>> OR ITS
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * For more information on this product, please see<<www.naonsoft.com>>
 */

package com.naon.study.post.controller;

import com.naon.framework.api.response.ResponseHelper;
import com.naon.framework.api.response.ResponseListHelper;
import com.naon.study.post.dto.PostDto;
import com.naon.study.post.service.PostService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 신석균(yanni @ naonsoft.com)
 */
@RestController
@RequestMapping("/api/posts")
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
