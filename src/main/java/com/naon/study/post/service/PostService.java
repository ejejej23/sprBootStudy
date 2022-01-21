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

package com.naon.study.post.service;

import com.naon.study.post.domain.Post;
import com.naon.study.post.domain.repositopry.PostRepository;
import com.naon.study.post.dto.PostDto;
import com.naon.framework.exception.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 신석균(yanni @ naonsoft.com)
 */
@RequiredArgsConstructor
@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostDto.Response createPost(final PostDto.Create dto) {
        return PostDto.Response.of(postRepository.save(dto.toEntity()));
    }

    @Transactional(readOnly = true)
    public PostDto.Response findPost(Long id) {
        return PostDto.Response.of(
                postRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

    public List<PostDto.ResponseList> findPosts(PostDto.Search dto) {
        return postRepository.findByTitleContainsAndPostDtAfter(dto.getTitle(), dto.getPostDt()).stream()
                .map(PostDto.ResponseList::of)
                .collect(Collectors.toList());
    }

    public PostDto.Response updatePost(final Long id, final PostDto.Update dto) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::new);
        return PostDto.Response.of(post.updatePost(dto));
    }

    public void deletePost(final Long id) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::new);
        postRepository.delete(post);

//        postRepository.deleteById(id);
    }
}
