package com.example.demokot.repository.board

import com.example.demokot.domain.dto.request.BoardModifyRequestDTO
import com.example.demokot.domain.dto.request.BoardRegisterRequestDTO
import com.example.demokot.domain.dto.response.BoardDTO
import com.example.demokot.domain.entity.sample.Board
import org.springframework.data.domain.Pageable

interface BoardRepository {

    /** 게시글조회 **/
    fun boardList(pageable: Pageable): List<Board>

    /** 게시글 상세 **/
    fun detailBoard(board_id:Long): BoardDTO?


    /** 게시글 수정 **/
    fun editBoard(dto: BoardModifyRequestDTO):Boolean



    /** 게시글 삭제 **/
    fun deleteBoard(userId: Long, board_id: Long):Boolean


    /**게시글 작성자 조회 **/
    fun findBoardCreateUserId(board_id: Long):Long?
}