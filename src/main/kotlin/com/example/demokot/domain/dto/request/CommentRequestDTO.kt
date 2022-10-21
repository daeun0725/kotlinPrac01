package com.example.demokot.domain.dto.request

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

class CommentRequestDTO @QueryProjection constructor(
    var userId: Long ,
    var boardId: Long,
    var comment: String,
) {
}