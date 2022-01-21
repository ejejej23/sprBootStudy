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

import com.naon.framework.base.BasePageable;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * @author SeokKyun(yanni @ naonsoft.com)
 */
public class ResponsePageHelper {
    private ApiResponseCode code;
    private Data data;

    public ResponsePageHelper(ApiResponseCode code, Data data) {
        this.code = code;
        this.data = data;
    }

    public static ResponsePageHelper of(Page<?> pages) {
        return new ResponsePageHelper(ApiResponseCode.OK, pages == null?new Data(): new Data(pages.getContent(), BasePageable.of(pages)));
    }

    public static <T> ResponsePageHelper of(Page<?> pages, T annex) {
        return new ResponsePageHelper(
                ApiResponseCode.OK,
                Optional.ofNullable(pages)
                        .map(page -> new Data(page.getContent(), BasePageable.of(page), annex))
                        .orElseGet(Data::new)
        );
    }

    @Getter
    @NoArgsConstructor
    static class Data<T> {
        private List<?> contents;
        private BasePageable page;
        private T annex;

        public Data(List<?> contents, BasePageable page) {
            this.contents = contents;
            this.page = page;
        }

        public Data(List<?> contents, BasePageable page, T annex) {
            this.contents = contents;
            this.page = page;
            this.annex = annex;
        }
    }
}
