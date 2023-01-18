package com.example.demokot.service.User

import com.example.demokot.domain.entity.User.Authority
import com.example.demokot.repository.User.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Component("userDetailsService")
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findOneWithAuthoritiesByUsername(username)
            .map { user: com.example.demokot.domain.entity.User.User -> createUser(username, user) }
            .orElseThrow { UsernameNotFoundException("$username -> 데이터베이스에서 찾을 수 없습니다.") }
    }

    private fun createUser(username: String, user: com.example.demokot.domain.entity.User.User): User {
        if (!user.isActivated) {
            throw RuntimeException("$username -> 활성화되어 있지 않습니다.")
        }

        val grantedAuthorities = user.authorities!!.stream()
            .map { authority: Authority -> SimpleGrantedAuthority(authority.authorityName) }
            .collect(Collectors.toList())

        return User(
            user.username,
            user.password,
            grantedAuthorities
        )
    }
}