package com.example.demokot.domain.dto.request

import com.example.demokot.domain.entity.sample.User
import com.querydsl.core.annotations.QueryProjection
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserDto  @QueryProjection constructor(
    @field:NotNull
    @field:Size(min = 3, max = 50)
    var username: String? = null,

    @field:NotNull
    @field:Size(min = 3, max = 100)
    var password: String? = null,

    @field:NotNull
    @field:Size(min = 3, max = 50)
    var nickname: String? = null,

    var isActivated:Boolean = false,

    var authorityDtoSet: Set<AuthorityDto>? = null
) {
    companion object {
        fun from(user: User): UserDto {
            return user.run {
                UserDto(
                    username = username,
                    nickname = nickname,
                    authorityDtoSet = user.authorities!!
                        .map { authority ->
                            AuthorityDto(authority.authorityName)
                        }
                        .toSet()
                )
            }
        }
    }
}