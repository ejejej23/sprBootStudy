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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 신석균(yanni @ naonsoft.com)
 */
@ToString
@NoArgsConstructor
@Getter
public class ResponseHelper<T> {
    public static final ResponseHelper<String> DEFAULT_OK = new ResponseHelper<>(ApiResponseCode.OK);
    public static final ResponseHelper<String> DEFAULT_UNAUTHORIZED = new ResponseHelper<>(ApiResponseCode.UNAUTHORIZED);

    private ApiResponseCode code;
    private String message;
    private T data;

    private ResponseHelper(ApiResponseCode status) {
        this.bindStatus(status);
    }

    private ResponseHelper(ApiResponseCode status, T data) {
        this.bindStatus(status);
        this.data = data;
    }

    private ResponseHelper(ApiResponseCode code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private void bindStatus(ApiResponseCode status) {
        this.code = status;
        this.message = status.getMessage();
    }

    public static <T> ResponseHelper<T> createOK(T data) {
        return new ResponseHelper<>(ApiResponseCode.OK, data);
    }
}
