package com.example.demokot.domain.entity.sample

import com.example.demokot.domain.dto.request.CommentRequestDTO
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
    val board: Board? = null,

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var user: User? = null,

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

    constructor(dto: CommentRequestDTO) : this(
        content = dto.comment,
        user = User(dto.userId),
        board = Board(dto.boardId)
    )
}