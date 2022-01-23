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

package com.naon.study.user.service;

import com.naon.study.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 신석균(yanni @ naonsoft.com)
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

//    public UserDto.Response createUser(final UserDto.Create dto) {
//        return UserDto.Response.of(userRepository.save(dto.toEntity()));
//    }
//
//    @Transactional(readOnly = true)
//    public UserDto.Response findUser(Long id) {
//        return UserDto.Response.of(
//                userRepository.findById(id).orElseThrow(NotFoundException::new)
//        );
//    }
//
//    public UserDto.Response updateUser(final Long id, final UserDto.Update dto) {
//        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
//        return UserDto.Response.of(user.updateUser(dto));
//    }
//
//    public void deleteUser(final Long id) {
//        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
//        userRepository.delete(user);
//    }
}
