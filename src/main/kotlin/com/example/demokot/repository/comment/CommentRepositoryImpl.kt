package com.example.demokot.repository.comment

import com.example.demokot.domain.dto.request.CommentRequestDTO
import com.example.demokot.domain.entity.sample.Comment
import com.example.demokot.domain.entity.sample.QComment.comment
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class CommentRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : CommentRepository {


    override fun editComment(dto: CommentRequestDTO): Boolean {
//        return queryFactory.select(comment)
//            .from(comment)
        return true
    }


}