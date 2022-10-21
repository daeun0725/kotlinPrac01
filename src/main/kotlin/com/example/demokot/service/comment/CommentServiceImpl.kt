package com.example.demokot.service.comment

import com.example.demokot.domain.dto.request.CommentRequestDTO
import com.example.demokot.domain.entity.sample.Comment
import com.example.demokot.repository.comment.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@Service
@Transactional(readOnly = true)
class CommentServiceImpl(
    val commentRepository: CommentRepository,
    val userRepository: CommentRepository,
    val em: EntityManager
) : CommentService {

    /**  댓글 작성 **/
    @Transactional
    override fun postComment(dto: CommentRequestDTO): Long? {
        val comment = Comment(dto)
        em.persist(comment)
        return comment.id
    }

    /** 댓글 수정 **/
    @Transactional
    override fun editComment(dto: CommentRequestDTO): Boolean {
        val result = commentRepository.editComment(dto)
        if (!result) throw Exception("failed")
        return result
    }

}