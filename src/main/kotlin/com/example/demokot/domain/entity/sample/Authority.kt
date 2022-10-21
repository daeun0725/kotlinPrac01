package com.example.demokot.domain.entity.sample

import javax.persistence.*

@Entity
@Table(name = "authority")
data class Authority(
    @Id @GeneratedValue @Column(name = "authority_name", columnDefinition = "TEXT", nullable = false)
    var authorityName: String? = null
)