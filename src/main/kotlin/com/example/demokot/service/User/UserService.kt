package com.example.demokot.service.User

import com.example.demokot.domain.dto.request.UserDto
import com.example.demokot.domain.dto.request.UserDto.Companion.from
import com.example.demokot.domain.entity.sample.Authority
import com.example.demokot.domain.entity.sample.User
import com.example.demokot.exception.DuplicateMemberException
import com.example.demokot.exception.NotFoundMemberException
import com.example.demokot.repository.User.UserRepository
import com.example.demokot.utill.SecurityUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun signup(userDto: UserDto): UserDto {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.username!!).orElse(null) != null) {
            throw DuplicateMemberException("이미 가입되어 있는 유저입니다.")
        }

        val authority = Authority(authorityName = "ROLE_USER")

        val user = User(
            username = userDto.username,
            password = passwordEncoder.encode(userDto.password),
            nickname = userDto.nickname,
            authorities = mutableListOf(authority),
            isActivated = true
        )

        return from(userRepository.save(user))
    }

    @Transactional(readOnly = true)
    fun getUserWithAuthorities(username: String): UserDto {
        return from(
            userRepository.findOneWithAuthoritiesByUsername(username)
                .orElse(null)
        )
    }

    @get:Transactional(readOnly = true)
    val myUserWithAuthorities: UserDto
        get() = from(
            SecurityUtil.currentUsername
                .flatMap { username: String ->
                    userRepository.findOneWithAuthoritiesByUsername(username)
                }
                .orElseThrow {
                    throw NotFoundMemberException("Member not found")
                }
        )
}