package com.example.demokot.service.board

import com.example.demokot.domain.dto.request.Community.BoardModifyRequestDTO
import com.example.demokot.domain.dto.request.Community.BoardRegisterRequestDTO
import com.example.demokot.domain.dto.response.BoardDTO
import com.example.demokot.domain.entity.community.Board
import com.example.demokot.repository.board.BoardRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.Id


@Service
@Transactional(readOnly = true)
class BoardServiceImpl(
    val boardRepository: BoardRepository,
    val em: EntityManager
) : BoardService {


    /**
     * 게시글 키워드 검색 조회
     **/

    override fun searchBoards(keyword: String?): List<BoardDTO> {
        return boardRepository.searchBoards(keyword)
    }

    /**
     * 작성글 리스트 조회
     **/
    override fun boardList(pageable: Pageable): List<Board> {
        return boardRepository.boardList(pageable)
    }

    /**
     * 작성글 상세조회
     **/
    override fun findDetailBoard(boardId: Long): BoardDTO {
        val findDetailBoard = boardRepository.findDetailBoard(boardId) ?: throw Exception("board 아이디 잘못 조회")

        return findDetailBoard
    }

    /**
     작성글 작성
     **/
    @Transactional
    override fun postBoard(dto: BoardRegisterRequestDTO): Long? {
        val board = Board(dto)
        em.persist(board)

        return board.id
    }


    /**
     *  작성글 수정
     **/
    @Transactional
    override fun editBoard(dto: BoardModifyRequestDTO): Boolean {
        val result = boardRepository.editBoard(dto)
        if (!result) throw Exception("존재하지 않는 게시판 요청")

        return result
    }


    /**
     *  작성글 삭제
     **/
    @Transactional
    override fun deleteBoard(userId:Long, board_id: Long): Boolean {
        val isDeleted = boardRepository.deleteBoard(userId,board_id)
        if (!isDeleted) {
            val boardCreateUserId = boardRepository.findBoardCreateUserId(board_id) ?: throw Exception(" 존재하지않는 글입니다")
        if( boardCreateUserId != userId) throw Exception(" 삭제 권환이 없습니다. ")
        }
        return isDeleted
    }
}

