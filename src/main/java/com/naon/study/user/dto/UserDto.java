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
    
package com.naon.study.user.dto;
import com.naon.study.user.domain.User;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalDate;


/**
 * @author 
 */
public class UserDto {
   @Getter
   @Builder
   @NoArgsConstructor(access = AccessLevel.PACKAGE)
   @AllArgsConstructor(access = AccessLevel.PACKAGE)
   public static class Create {
      private String email;//전자우편
      private String password;//비밀번호
      private String userName;//사용자명
      private LocalDate birthday;//생일
      private String role;//역할

       public User toEntity() {
           return User.builder()
                  .email(email).password(password).userName(userName).birthday(birthday)
.role(role)
           .build();
       }
   }

   @Getter
   @Builder
   @NoArgsConstructor(access = AccessLevel.PACKAGE)
   @AllArgsConstructor(access = AccessLevel.PACKAGE)
   public static class Update {
      private String email;//전자우편
      private String password;//비밀번호
      private String userName;//사용자명
      private LocalDate birthday;//생일
      private String role;//역할

   }

   @Getter @Setter
   @Builder
   @NoArgsConstructor(access = AccessLevel.PACKAGE)
   //@AllArgsConstructor(access = AccessLevel.PACKAGE)
   public static class Search {
   }

   @Getter
   @Builder
   @NoArgsConstructor(access = AccessLevel.PACKAGE)
   @AllArgsConstructor(access = AccessLevel.PACKAGE)
   public static class Response {
      private Long userId;//사용자고유번호
      private String email;//전자우편
      private String password;//비밀번호
      private String userName;//사용자명
      private LocalDate birthday;//생일
      private String role;//역할
      private LocalDateTime regDt;//등록일시
      private LocalDateTime updDt;//수정일시


       public static Response of(final User user) {
           return Response.builder()
                  .userId(user.getUserId())
.email(user.getEmail()).password(user.getPassword()).userName(user.getUserName()).birthday(user.getBirthday())
.role(user.getRole()).regDt(user.getRegDt()).updDt(user.getUpdDt())
           .build();
       }
   }
   @Getter
   @NoArgsConstructor(access = AccessLevel.PACKAGE)
   public static class ResponseList {
      private Long userId;//사용자고유번호
      private String email;//전자우편
      private String password;//비밀번호
      private String userName;//사용자명
      private LocalDate birthday;//생일
      private String role;//역할
      private LocalDateTime regDt;//등록일시
      private LocalDateTime updDt;//수정일시


       @Builder
       public ResponseList(Long userId ,String email ,String password ,String userName ,LocalDate birthday ,String role ,LocalDateTime regDt ,LocalDateTime updDt) {
             this.userId = userId;
             this.email = email;
             this.password = password;
             this.userName = userName;
             this.birthday = birthday;
             this.role = role;
             this.regDt = regDt;
             this.updDt = updDt;

       }
   }
}
