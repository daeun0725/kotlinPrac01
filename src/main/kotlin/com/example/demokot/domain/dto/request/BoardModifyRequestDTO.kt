package com.example.demokot.domain.dto.request

import java.time.LocalDateTime

class BoardModifyRequestDTO (
    var board_id: Long,
    var userId: Long,
    var title: String,
    var content: String,
) {
}