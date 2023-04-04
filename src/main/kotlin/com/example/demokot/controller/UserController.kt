package com.example.demokot.controller

import com.example.demokot.domain.dto.request.UserDto
import com.example.demokot.service.User.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.io.IOException
import javax.persistence.EntityManager
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService, private val em: EntityManager) {

    @GetMapping("/hello")
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("hello")
    }

    @PostMapping("/test-redirect")
    @Throws(IOException::class)
    fun testRedirect(response: HttpServletResponse) {
        response.sendRedirect("/api/user")
    }

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid userDto: UserDto): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.signup(userDto))
    }


    /** 관리자 **/
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    fun getMyUserInfo(request: HttpServletRequest): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.myUserWithAuthorities)
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun getUserInfo(@PathVariable username: String): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username))
    }
}