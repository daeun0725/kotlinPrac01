package com.example.demokot.domain.dto.response

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

/*** 댓글 조회 응답 DTO**/
class CommentResponseDTO @QueryProjection constructor(

    var userId: Long ,
    var boardId: Long,
    var commentId: Long,
    var comment: String,
    var createdDate: LocalDateTime


){
}