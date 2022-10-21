package com.example.demokot.repository.comment

import com.example.demokot.domain.dto.request.CommentRequestDTO

interface CommentRepository {


    fun editComment(dto: CommentRequestDTO): Boolean
}