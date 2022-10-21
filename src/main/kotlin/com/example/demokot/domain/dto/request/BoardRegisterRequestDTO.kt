package com.example.demokot.domain.dto.request

import java.time.LocalDateTime

class BoardRegisterRequestDTO (
    var userId: Long,
    var title: String,
    var content: String,
) {
}