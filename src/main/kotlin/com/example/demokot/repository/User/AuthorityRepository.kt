package com.example.demokot.repository.User

import com.example.demokot.domain.entity.sample.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository : JpaRepository<Authority?, String?>