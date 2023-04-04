package com.example.demokot.repository.board

import com.example.demokot.domain.dto.request.Community.BoardModifyRequestDTO
import com.example.demokot.domain.dto.response.BoardDTO
import com.example.demokot.domain.entity.community.Board
import org.springframework.data.domain.Pageable

interface BoardRepository {

    /**
     * 게시글 검색
     */

    fun searchBoards(keyword: String?): List<BoardDTO>


    /**
     *  게시글 리스트 조회
     **/
    fun boardList(pageable: Pageable): List<Board>


    /**
     *  게시글 상세조회
     **/
    fun findDetailBoard(board_id:Long): BoardDTO?


    /**
     * 게시글 수정
     **/
    fun editBoard(dto: BoardModifyRequestDTO):Boolean



    /**
     * 게시글 삭제
     **/
    fun deleteBoard(userId: Long, boardId: Long):Boolean


    /**
     * 게시글 작성자 조회
     **/
    fun findBoardCreateUserId(boardId: Long):Long?
}