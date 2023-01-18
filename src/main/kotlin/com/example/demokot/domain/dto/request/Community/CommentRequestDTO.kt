package com.example.demokot.domain.dto.request.Community

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

class CommentRequestDTO @QueryProjection constructor(
    var userId: Long ,
    var board_id: Long,
    var comment: String,
) {
}