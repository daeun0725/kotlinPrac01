package com.example.demokot.service.comment

import com.example.demokot.domain.dto.request.CommentRequestDTO
import com.example.demokot.domain.entity.sample.Board


interface CommentService {

    //fun getComment(comment_id: Long): List<Board>

    /** 작성 **/
    fun postComment(dto: CommentRequestDTO): Long?

    /** 댓글 수정 **/
    fun editComment(dto: CommentRequestDTO): Boolean
}