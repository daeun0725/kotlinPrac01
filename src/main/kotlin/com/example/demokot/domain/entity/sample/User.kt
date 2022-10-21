package com.example.demokot.domain.entity.sample

import com.example.demokot.domain.dto.request.UserDto
import javax.persistence.*

@Entity
@Table(name = "`user`")
data class User(
    @Id @Column(name = "user_id") @GeneratedValue
    var userId: Long? = null,

    @Column(name = "username", length = 50, unique = true)
    var username: String? = null,

    @Column(name = "password", length = 100)
    var password: String? = null,

    @Column(name = "nickname", length = 50)
    var nickname: String? = null,

    @Column(name = "activated")
    var isActivated: Boolean = false,

    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_name", referencedColumnName = "authority_name")]
    )
    var authorities: MutableList<Authority> = mutableListOf()
) {

    constructor(dto: UserDto) : this(
            username = dto.username,
            password = dto.password,
            nickname = dto.nickname,
            isActivated = dto.isActivated,
        )
}