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

package com.naon.study.post.domain;

//import com.naon.study.post.dto.PostDto;
import com.naon.framework.base.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.naon.study.post.dto.PostDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * @author 신석균(yanni @ naonsoft.com)
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post extends BaseTimeEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long posId;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column
    private String author;

    @Column(nullable = false)
    LocalDateTime postDt;

    @Builder
    public Post(final String title, final String content, final String author, final LocalDateTime postDt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.postDt = postDt;
    }

    public Post updatePost(final PostDto.Update dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.author = dto.getAuthor();

        return this;
    }

    public Post updateTitle(final String title) {
        this.title = title;

        return this;
    }

    @Override
    public Long getId() {
        return posId;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
