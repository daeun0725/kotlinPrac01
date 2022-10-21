package com.example.demokot.repository.board

import com.example.demokot.domain.dto.request.BoardModifyRequestDTO
import com.example.demokot.domain.dto.response.BoardDTO
import com.example.demokot.domain.dto.response.QBoardDTO
import com.example.demokot.domain.entity.sample.Board
import com.example.demokot.domain.entity.sample.QBoard.board
import com.example.demokot.domain.entity.sample.QUser.user
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class BoardRepositoryImpl : BoardRepository {

    @Autowired
    lateinit var queryFactory: JPAQueryFactory

    /** 리스트 전체 조회 **/
    override fun boardList(pageable: Pageable): List<Board> {
        return queryFactory.select(board)
            .from(board)
            .offset(pageable.offset)
            .orderBy(board.title.desc())
            .fetch()

        // return emptyList()
    }


    /**게시글 작성자 조회**/
    override fun findBoardCreateUserId(board_id: Long): Long? {
        return queryFactory.select(board.user.userId)
            .from(board)
            .where(board.id.eq(board_id))
            .fetchFirst()
    }


    /** 작성글 상세 보기 **/
    override fun detailBoard(board_id: Long): BoardDTO? {
        return queryFactory.select(
            QBoardDTO(
                board.id,
                board.title,
                board.content,
                board.createdAt,
                board.modifiedAt,
                user.userId
            )
        ).from(board)
            .where(board.id.eq(board_id))
            .fetchFirst()
    }


    /** 작성글 삭제하기  **/
    override fun deleteBoard(userId: Long, board_id: Long): Boolean {
        return queryFactory.delete(board)
            .where(board.id.`in`(board_id))
            .execute() == 1L
    }


    /**  작성글 수정하기  **/
    override fun editBoard(dto: BoardModifyRequestDTO): Boolean {
        return queryFactory.update(board)
            .where(board.id.eq(dto.board_id))
            .where(board.user.userId.eq(dto.userId))
            .set(board.title, dto.title)
            .set(board.content, dto.content)
            .execute() == 1L
    }
}