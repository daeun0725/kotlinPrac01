package com.example.demokot.domain.dto.request.Community

import java.time.LocalDateTime

class BoardRegisterRequestDTO (
    var userId: Long,
    var title: String,
    var content: String,
    var boardId: Long,
    var createdAtDate: LocalDateTime
) {
}