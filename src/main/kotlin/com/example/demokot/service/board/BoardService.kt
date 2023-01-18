package com.example.demokot.service.board

import com.example.demokot.domain.dto.request.Community.BoardModifyRequestDTO
import com.example.demokot.domain.dto.request.Community.BoardRegisterRequestDTO
import com.example.demokot.domain.dto.response.BoardDTO
import com.example.demokot.domain.entity.community.Board
import org.springframework.data.domain.Pageable

interface BoardService {


    /**  조회 **/
    fun boardList(pageable: Pageable): List<Board>

    /** 상세  **/
    fun detailBoard(board_id: Long): BoardDTO


    /** 작성 **/
    fun postBoard(dto: BoardRegisterRequestDTO): Long?


    /** 수정 **/
    fun editBoard(dto: BoardModifyRequestDTO): Boolean


    /** 삭제 **/
    fun deleteBoard(userId:Long, board_id: Long): Boolean
}


