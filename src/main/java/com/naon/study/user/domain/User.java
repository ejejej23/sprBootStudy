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
    
package com.naon.study.user.domain;

import com.naon.framework.annotation.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.LocalDate;


/**
 * @author 
 */
@Entity
@Table(name = "user")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Persistable<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  @Comment(value = "사용자고유번호")
  private Long userId;

  @Column(name = "email")
  @Comment(value = "전자우편")
  private String email;

  @Column(name = "password")
  @Comment(value = "비밀번호")
  private String password;

  @Column(name = "user_name")
  @Comment(value = "사용자명")
  private String userName;

  @Column(name = "birthday")
  @Comment(value = "생일")
  private LocalDate birthday;

  @Column(name = "role")
  @Comment(value = "역할")
  private String role;

  @CreatedDate
  @Column(name = "reg_dt")
  @Comment(value = "등록일시")
  private LocalDateTime regDt;

  @LastModifiedDate
  @Column(name = "upd_dt")
  @Comment(value = "수정일시")
  private LocalDateTime updDt;


  @Builder
  public User(String email,String password,String userName,LocalDate birthday,String role) {
    this.email = email;
    this.password = password;
    this.userName = userName;
    this.birthday = birthday;
    this.role = role;

  }
//  public User updateUser(UserDto.Update dto) {
//    Optional.ofNullable(dto.getEmail()).ifPresent(email -> this.email = email);
//    Optional.ofNullable(dto.getPassword()).ifPresent(password -> this.password = password);
//    Optional.ofNullable(dto.getUserName()).ifPresent(userName -> this.userName = userName);
//    Optional.ofNullable(dto.getBirthday()).ifPresent(birthday -> this.birthday = birthday);
//    Optional.ofNullable(dto.getRole()).ifPresent(role -> this.role = role);
//
//    return this;
//  }

  @Override
  public Long getId() {
      return userId;
  }

  @Override
  public boolean isNew() {
      return true;
  }
}
