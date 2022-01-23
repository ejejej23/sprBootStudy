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

package com.naon.study.post.dto;

import com.naon.study.post.domain.Post;
import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;

/**
 * @author 신석균(yanni @ naonsoft.com)
 */
public class PostDto {
    @Getter
    public static class Create {
        @NotEmpty
        @Size(max = 100)
        private String title;
        private String content;
        @NotEmpty
        @Size(min = 2, max = 10)
        private String author;
        private LocalDateTime postDt;

        @Builder
        public Create(String title, String content, String author, LocalDateTime postDt) {
            this.title = title;
            this.content = content;
            this.author = author;
            this.postDt = postDt;
        }

        public Post toEntity() {
            return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .postDt(postDt)
                .build();
        }
    }

    @Getter
    public static class Update {
        @Size(max = 100)
        private String title;
        @Size(min = 2, max = 10)
        private String content;
        private String author;

        @Builder
        public Update(String title, String content, String author) {
            this.title = title;
            this.content = content;
            this.author = author;
        }
    }


    @Getter @Setter
    public static class Search {
        private String title;
        private LocalDateTime postDt;
    }

    @Getter
    public static class Response {
        private Long postId;
        private String title;
        private String content;
        private String author;
        private LocalDateTime postDt;
        private LocalDateTime regDt;
        private LocalDateTime updDt;

        @Builder
        public Response(Long postId, String title, String content, String author, LocalDateTime postDt, LocalDateTime regDt, LocalDateTime updDt) {
            this.postId = postId;
            this.title = title;
            this.content = content;
            this.author = author;
            this.postDt = postDt;
            this.regDt = regDt;
            this.updDt = updDt;
        }

        public static Response of(final Post post) {
            return Response.builder()
                .postId(post.getPosId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .postDt(post.getPostDt())
                .regDt(post.getRegDt())
                .updDt(post.getUpdDt())
                .build();
        }
    }

    @Getter
    public static class ResponseList {
        private final Long postId;
        private final String title;
        private final String content;
        private final String author;

        @Builder
        public ResponseList(Long postId, String title, String content, String author) {
            this.postId = postId;
            this.title = title;
            this.content = content;
            this.author = author;
        }

        public static ResponseList of(final Post post) {
            return ResponseList.builder()
                .postId(post.getPosId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .build();
        }
    }
}





