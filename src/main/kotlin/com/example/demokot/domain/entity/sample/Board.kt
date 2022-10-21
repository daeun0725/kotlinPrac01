package com.example.demokot.domain.entity.sample

import com.example.demokot.domain.dto.request.BoardRegisterRequestDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@AttributeOverride(name = "id", column = Column(name = "board_id"))
@EntityListeners(AuditingEntityListener::class)
class Board(
    @Id @GeneratedValue @Column(name = "board_id")
    val id: Long? = null,

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var user: User? = null,

    @Column(columnDefinition = "TEXT", nullable = false)
    val title: String? = null,

    @Column(columnDefinition = "TEXT", nullable = false)
    val content: String? = null,

    @CreatedDate
    @Column(nullable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(nullable = false)
    var modifiedAt: LocalDateTime? = null,
) {

    constructor(dto: BoardRegisterRequestDTO) : this(
        title = dto.title,
        content = dto.content,
        user = User(dto.userId)
    )


}