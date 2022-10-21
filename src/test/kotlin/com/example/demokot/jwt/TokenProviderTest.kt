package com.example.demokot.jwt

import com.example.demokot.domain.dto.request.UserDto
import com.example.demokot.service.User.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

@SpringBootTest
internal class TokenProviderTest {

    @Autowired
    lateinit var tokenProvider: TokenProvider

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var authenticationManagerBuilder: AuthenticationManagerBuilder

    @Test
    fun test() {
        val username = "burger"
        val password = "123123"
        userService.signup(UserDto(username, password))
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)
        val authentication: Authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken)
        SecurityContextHolder.getContext().setAuthentication(authentication)
        val jwt = tokenProvider.createToken(authentication)

        println("@@@@@@@@@@@@@@@@@@ jwt = [$jwt] @@@@@@@@@@@@@@@@@@")
    }

}