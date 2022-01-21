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

package com.naon.framework.api.response;

import com.naon.framework.EnumType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 신석균(yanni @ naonsoft.com)
 */
@Getter
@RequiredArgsConstructor
public enum ApiResponseCode implements EnumType {
    // 200 OK
    OK("요청이 성공하였습니다."),
    BUSSINESS_EXCEPTION("도메인에서 발생하는 에러는 아니지만 OK와는 다르게 처리하는 예외들."),
    // 400 Bad Request
    BAD_PARAMETER("잘못된 요청입니다(400)."),
    FILE_SIZE_EXCEEDED( "사진 한 장의 크기는 10MB 보다 클 수 없습니다."),
    //401 Unauthorized
    UNAUTHORIZED("로그인 정보가 없습니다."),
    AUTHENTICATION_FAILED("로그인 정보가 없습니다(토큰기간만료)."),
    TOKEN_GENERATION_FAILED("로그인 정보가 없습니다(토큰생성실패)."),
    TOKEN_VALID_FAILED("로그인 정보가 없습니다(토큰검증실패)"),
    LOGIN_FAILED("인증에 실패하였습니다."),
    // 403 Forbidden
    FORBIDDEN("접근 권한이 없습니다(403)."),
    //404 Not Found
    LOGINID_NOT_FOUND("로그인 아이디가 존재하지 않습니다."),
    NOT_FOUND("요청한 자원이 존재하지 않습니다(404)."),
    // 409 Conflict
    LOGINID_DUPLICATION("동일한 아이디가 존재합니다."),
    EMAIL_DUPLICATION("동일한 이메일 주소가 존재합니다."),
    //415 Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE("지원하지 않는 미디어 유형입니다(415)."),
    //500 Internal Server Error
    SERVER_ERROR("서버 오류가 발생하였습니다(500)."),
    //507 Insufficient Storage
    INSUFFICIENT_STORAGE( "서버의 저장 공간이 부족합니다.(507)"),
    // 현재 사용안함
    NO_CONTENTS("송신할 컨텐츠가 없습니다"),
    INVALID_TYPE_VALUE("잘못된 유형 값 입니다."),
    INVALID_INPUT_VALUE("잘못된 입력 값 입니다."),
    PASSWORD_FAILED_EXCEEDED("비밀번호 실패 횟수를 초과했습니다.");

    private final String message;

    public String getId() {
        return name();
    }

    @Override
    public String getText() {
        return message;
    }
}
