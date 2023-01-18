package com.example.demokot.repository.User

import com.example.demokot.domain.entity.User.User
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User?, Long?> {
    //lazy 조회가 아니라 eager조회로 authorities 정보도 같이 가져온다.
    @EntityGraph(attributePaths = ["authorities"])
    //username 기준으로 user를 가져올 때 권환 정보도 같이 가져온다.
    fun findOneWithAuthoritiesByUsername(username: String): Optional<User>
}