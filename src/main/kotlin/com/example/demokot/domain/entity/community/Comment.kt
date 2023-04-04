package com.example.demokot.domain.entity.community

import com.example.demokot.domain.dto.response.CommentResponseDTO
import com.example.demokot.domain.entity.User.User
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@AttributeOverride(name = "id", column = Column(name = "comment_id"))
@EntityListeners(AuditingEntityListener::class)
class Comment(
    @Id @GeneratedValue @Column(name = "comment_id")
    val id: Long? = null,

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val boardId: Board? = null,

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var userId: User? = null,

    @Column(columnDefinition = "TEXT", nullable = false)
    val content: String? = null,

    @CreatedDate
    @Column(nullable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(nullable = false)
    var modifiedAt: LocalDateTime? = null,

    @JoinColumn(name = "parent_comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val parentComment: Comment? = null,
) {

    constructor(dto: CommentResponseDTO) : this(

        id = dto.commentId,
        content = dto.comment,
        userId = User(dto.userId),
        boardId = Board(dto.boardId),
        createdAt = dto.createdDate

    )
}