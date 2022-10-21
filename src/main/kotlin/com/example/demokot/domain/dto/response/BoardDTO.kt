package com.example.demokot.domain.dto.response

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

class BoardDTO  @QueryProjection constructor(
    var board_id: Long,
    var title: String,
    var content: String,
    var createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime,

    var userId: Long,
)