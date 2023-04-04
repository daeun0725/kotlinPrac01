package com.example.demokot.repository.board

import com.example.demokot.domain.dto.request.Community.BoardModifyRequestDTO
import com.example.demokot.domain.dto.response.BoardDTO
import com.example.demokot.domain.dto.response.QBoardDTO
import com.example.demokot.domain.entity.User.QUser.user
import com.example.demokot.domain.entity.community.Board
import com.example.demokot.domain.entity.community.QBoard.board
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class BoardRepositoryImpl : BoardRepository {

    @Autowired
    lateinit var queryFactory: JPAQueryFactory


    /**
     * 게시글 검색
     */
    override fun searchBoards(keyword: String?): List<BoardDTO> {
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
                .where(
                        board.title.containsIgnoreCase(keyword),
                        board.content.containsIgnoreCase(keyword)
                )
                .orderBy(board.createdAt.desc())
                .fetch()
    }



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
    override fun findBoardCreateUserId(boardId: Long): Long? {
        return queryFactory.select(board.user.userId)
            .from(board)
            .where(board.id.eq(boardId))
            .fetchFirst()
    }


    /** 작성글 상세 보기 **/
    override fun findDetailBoard(boardId: Long): BoardDTO? {
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
            .where(board.id.eq(boardId))
            .innerJoin(board.user, user)
            .fetchFirst()
    }


    /**
     *작성글 삭제하기
     **/
    override fun deleteBoard(userId: Long, boardId: Long): Boolean {
        return queryFactory.delete(board)
            .where(board.id.`in`(boardId), board.user.userId.eq(userId))
            .execute() == 1L
    }


    /**
     * 작성글 수정하기
     **/
    override fun editBoard(dto: BoardModifyRequestDTO): Boolean {
        return queryFactory.update(board)
            .where(board.id.eq(dto.board_id))
            .where(board.user.userId.eq(dto.userId))
            .set(board.title, dto.title)
            .set(board.content, dto.content)
            .set(board.modifiedAt, LocalDateTime.now())
            .execute() == 1L
    }
}

